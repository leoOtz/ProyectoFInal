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
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class Recaudo extends AppCompatActivity {
    private Bus u;
    private ec.edu.epn.proyecto2.Objetos.Recaudo rO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racaudo_1);
        u = (Bus)getIntent().getSerializableExtra("bus");
        rO = (ec.edu.epn.proyecto2.Objetos.Recaudo)getIntent().getSerializableExtra("fecha");
    }
    public void editarRecaudo(View v)
    {
        Intent i = new Intent(this, EditarRecaudo.class);
        i.putExtra("bus", u);
        i.putExtra("fecha",rO);
        Log.v("bus", u.getPlaca());
        startActivity(i);
    }
    public void eliminarRecaudo(View v)
    {
        ec.edu.epn.proyecto2.Objetos.Recaudo rN = rO;
        Log.v("BUS", u.getPlaca());
        EliminarRest eB =new EliminarRest();
        eB.execute(rN);
        Intent i = new Intent(this, RecaudoSegundo.class);
        i.putExtra("bus", u);
        startActivity(i);
    }
    public class EliminarRest extends AsyncTask<ec.edu.epn.proyecto2.Objetos.Recaudo,Void,String>
    {
        @Override
        protected String doInBackground(ec.edu.epn.proyecto2.Objetos.Recaudo... recaudos) {
            final String url = DireccionIP.ip+"SvrRecaudo/elimarRecaudo?" +
                    "info={pcla}&fecha={f}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ec.edu.epn.proyecto2.Objetos.Recaudo rB = recaudos[0];
            Log.v("bus", u.getPlaca());
            String r = restTemplate.getForObject(url,String.class,
                    u.getPlaca(),
                    rB.getFecha()
            );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Recaudo.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
