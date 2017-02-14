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

import ec.edu.epn.proyecto2.Adaptador.BusAdapter;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class RecaudoInicio extends AppCompatActivity {

    private ListView lvUnidades;
    private Bus datos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaudo_inicio);
        lvUnidades = (ListView)findViewById(R.id.lvUnidades);
        lvUnidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Bus bus = datos[posicion];
                Toast.makeText(RecaudoInicio.this,"Bus: " +bus.getNombre(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(RecaudoInicio.this,RecaudoSegundo.class);
                i.putExtra("bus", bus);

                startActivity(i);
            }
        });
    }
    public void inicio(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void onResume()
    {
        super.onResume();
       ConsultarBuses cB= new ConsultarBuses();
        cB.execute();
    }
    public class ConsultarBuses extends AsyncTask<Void,Void,Bus[]>
    {
        @Override
        protected Bus[] doInBackground(Void... Void) {
            final String url = DireccionIP.ip+"SrvBus/consultarBuses";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Bus [] r= restTemplate.getForObject(url,Bus[].class);
            return r;
        }
        @Override
        protected void onPostExecute(Bus[] b) {
            super.onPostExecute(b);
            datos =b;
            BusAdapter ba = new BusAdapter(RecaudoInicio.this,datos);
            lvUnidades.setAdapter(ba);
            //  Toast.makeText(GestionUnidades.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
