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

import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;

public class EditarRecaudo extends AppCompatActivity
{
    EditText monto,fecha;
    Bus u;
    ec.edu.epn.proyecto2.Objetos.Recaudo rO;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_recaudo);
        monto = (EditText) findViewById(R.id.txtRecaudo);
        fecha = (EditText)findViewById(R.id.txtfecha);
        u =(Bus) getIntent().getSerializableExtra("bus");
        rO = (ec.edu.epn.proyecto2.Objetos.Recaudo) getIntent().getSerializableExtra("recaudo");
        monto.setText(rO.getRecaudo());
        fecha.setText(rO.getFecha());
    }

    //Guardar del ONclick
    public void guardar(View v)
    {
        EditarRecaudoREST eH = new EditarRecaudoREST();
        ec.edu.epn.proyecto2.Objetos.Recaudo rA= new ec.edu.epn.proyecto2.Objetos.Recaudo();
        rA.setRecaudo(monto.getText().toString());
        rA.setFecha(fecha.getText().toString());
        eH.execute(rA);
        Intent i = new Intent (this, SubMenuHistorial.class);
        i.putExtra("bus",u);
        startActivity(i);
    }
    
    //Meotod RES actualizar
    public class EditarRecaudoREST extends AsyncTask<ec.edu.epn.proyecto2.Objetos.Recaudo,Void,String>
    {
        @Override
        protected String doInBackground(ec.edu.epn.proyecto2.Objetos.Recaudo... recaudos) {
            final String url = DireccionIP.ip+"SvrRecaudo/ActualizarRecaudo?" +
                    "info={pcla}&fecha={f}" +
                    "&fechaN={fN}&recaudo=443";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ec.edu.epn.proyecto2.Objetos.Recaudo rN = recaudos[0];
            String r = restTemplate.getForObject(url,String.class,
                    u.getPlaca(),
                    rO.getFecha(),
                    rN.getFecha(),
                    rN.getRecaudo()
            );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(EditarRecaudo.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
