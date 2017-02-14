package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class SubHistorialFecha extends AppCompatActivity {

    private Bus u;
    private Fecha f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_historial_fecha);
        u = (Bus)getIntent().getSerializableExtra("bus");
        f = (Fecha)getIntent().getSerializableExtra("fecha");
    }
    public void editarHistorico(View v)
    {
        Intent i = new Intent(this, EditatHistorico.class);
        i.putExtra("bus", u);
        i.putExtra("fecha",f);
        Log.v("bus", u.getPlaca());
        startActivity(i);
    }
    public void eliminar(View v)
    {
        Fecha uB = f;
        Log.v("BUS", u.getPlaca());
        Log.v("Fecha", f.getHoraSalida());
        EliminarRest eB =new EliminarRest();
        eB.execute(uB);
        Intent i = new Intent(this, SubMenuHistorial.class);
        i.putExtra("bus", u);
        startActivity(i);
    }
    public class EliminarRest extends AsyncTask<Fecha,Void,String>
    {
        @Override
        protected String doInBackground(Fecha... fechas) {
            final String url = DireccionIP.ip+"SrvFecha/elimarfecha?"+
                    "info={p}&fecha={f}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Fecha fB = fechas[0];
            Log.v("bus", u.getPlaca());
            String r = restTemplate.getForObject(url,String.class,
                    u.getPlaca(),
                    fB.getHoraSalida()
            );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(SubHistorialFecha.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
