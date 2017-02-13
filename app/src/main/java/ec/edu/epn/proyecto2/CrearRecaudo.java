package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Objetos.*;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class CrearRecaudo extends AppCompatActivity
{
    EditText monto,fecha;
    Bus u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_recaudo);
        monto = (EditText) findViewById(R.id.txtMonto);
        fecha = (EditText)findViewById(R.id.txtfecha);
        u =(Bus) getIntent().getSerializableExtra("bus");
    }
    public void guardar(View v)
    {
        ec.edu.epn.proyecto2.Objetos.Recaudo rN = new ec.edu.epn.proyecto2.Objetos.Recaudo();
        rN.setRecaudo(monto.getText().toString());
        rN.setFecha(fecha.getText().toString());
        GuardarRest gF= new GuardarRest();
        gF.execute(rN);
        Intent i = new Intent (this, SubMenuHistorial.class);
        i.putExtra("bus",u);
        startActivity(i);
    }
    public class GuardarRest extends AsyncTask<ec.edu.epn.proyecto2.Objetos.Recaudo,Void,String>
    {
        @Override
        protected String doInBackground(ec.edu.epn.proyecto2.Objetos.Recaudo... recaudos) {
            final String url = DireccionIP.ip+"SvrRecaudo/insertarRecaudo?" +
                    "recaudo={monto}&info={pcla}&fecha={}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().
                    add(new StringHttpMessageConverter());
            ec.edu.epn.proyecto2.Objetos.Recaudo rN = recaudos[0];
            String r = restTemplate.getForObject(url,String.class,
                    rN.getRecaudo(),
                    u.getPlaca(),
                    rN.getFecha()

            );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(CrearRecaudo.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
