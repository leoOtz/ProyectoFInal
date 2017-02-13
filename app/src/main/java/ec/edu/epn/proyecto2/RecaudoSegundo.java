package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Adaptador.AdaptadorRecaudo;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class RecaudoSegundo extends AppCompatActivity {

    ListView lvRecaudo;
    private ec.edu.epn.proyecto2.Objetos.Recaudo  datos[];
    private Bus u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaudo_segundo);
        lvRecaudo = (ListView)findViewById(R.id.lvRecaudo);
        u = (Bus)getIntent().getSerializableExtra("bus");
        lvRecaudo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                ec.edu.epn.proyecto2.Objetos.Recaudo recaudo = datos[posicion];
                Toast.makeText(RecaudoSegundo.this,"fecha: " +u.getNombre(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(RecaudoSegundo.this,Recaudo.class);
                i.putExtra("bus", u);
                i.putExtra("recaudo", recaudo);
                startActivity(i);
            }
        });
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
    public class ConsultarFecha extends AsyncTask<Void,Void, ec.edu.epn.proyecto2.Objetos.Recaudo[]>
    {
        @Override
        protected ec.edu.epn.proyecto2.Objetos.Recaudo[] doInBackground(Void... Void) {
            final String url = DireccionIP.ip+"SvrRecaudo/Consultar?info=={plca}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ec.edu.epn.proyecto2.Objetos.Recaudo [] r= restTemplate.getForObject(url,ec.edu.epn.proyecto2.Objetos.Recaudo[].class,
                    u.getPlaca());
            return r;
        }
        @Override
        protected void onPostExecute(ec.edu.epn.proyecto2.Objetos.Recaudo[] r) {
            super.onPostExecute(r);
            datos =r;
            AdaptadorRecaudo ra = new AdaptadorRecaudo(RecaudoSegundo.this,datos);
            lvRecaudo.setAdapter(ra);
            //  Toast.makeText(GestionUnidades.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
