package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Adaptador.AdaptadorRecaudo;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.RecaudoVo;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class RecaudoSegundo extends AppCompatActivity {

    ListView lvRecaudo;
    private RecaudoVo datos[];
    private Bus u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaudo_segundo);
        lvRecaudo = (ListView)findViewById(R.id.lvRecaudo);
        u = (Bus)getIntent().getSerializableExtra("bus");
        lvRecaudo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                RecaudoVo recaudo1 = datos[posicion];
                    Log.v("recaudo",recaudo1.getFecha());
                //Toast.makeText(RecaudoSegundo.this,"nombre: " +u.getNombre(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(RecaudoSegundo.this,Recaudo.class);
                i.putExtra("bus", u);
                i.putExtra("recaudo", recaudo1);
                startActivity(i);
            }
        });

    }
    public void onResume()
    {
        super.onResume();
        ConsultarRecaudo cB= new ConsultarRecaudo();
        cB.execute();

    }
    public void inicio(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void CrearRecaudo(View view)
    {
        Intent i = new Intent(this, CrearRecaudo.class);
        i.putExtra("bus",u);
        startActivity(i);

    }
    public class ConsultarRecaudo extends AsyncTask<Void,Void, RecaudoVo[]>
    {
        @Override
        protected RecaudoVo[] doInBackground(Void... Void) {
            final String url = DireccionIP.ip+"SvrRecaudo/Consultar?info={plca}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            RecaudoVo[] r= restTemplate.getForObject(url,RecaudoVo[].class,
                    u.getPlaca());
            return r;
        }
        @Override
        protected void onPostExecute(RecaudoVo[] r) {
            super.onPostExecute(r);
            datos =r;
            AdaptadorRecaudo ra = new AdaptadorRecaudo(RecaudoSegundo.this,datos);
            lvRecaudo.setAdapter(ra);
            //  Toast.makeText(GestionUnidades.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
