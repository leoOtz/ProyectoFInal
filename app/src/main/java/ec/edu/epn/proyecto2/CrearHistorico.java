package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;
import ec.edu.epn.proyecto2.sqlite.BusOH;
import ec.edu.epn.proyecto2.sqlite.FechaContract;

public class CrearHistorico extends AppCompatActivity {

    EditText fecha1,fecha2;
    Bus u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_historico);
        fecha1 = (EditText) findViewById(R.id.txtfecha1);
        fecha2 = (EditText)findViewById(R.id.txtfecha2);
        u =(Bus) getIntent().getSerializableExtra("bus");

    }
    public void guardar(View v)
    {
        /*
        BusOH foh = new BusOH(getApplicationContext());
        SQLiteDatabase sdb = foh.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put(FechaContract.Fecha.FECHA_LLEGADA,
                fecha2.getText().toString());
        datos.put(FechaContract.Fecha.FECHA_SALIDA,
                fecha1.getText().toString());
        datos.put(FechaContract.Fecha.INFO_BUS,
                u.getPlaca());


        sdb.insert(FechaContract.Fecha.NOMBRE_TABLA,
                null,
                datos);
        sdb.close();
        */
        Fecha fN = new Fecha();
        fN.setHoraLlegada(fecha2.getText().toString());
        fN.setHoraSalida(fecha1.getText().toString());
        GuardarRest gF= new GuardarRest();
        gF.execute(fN);
        Intent i = new Intent (this, SubMenuHistorial.class);
        i.putExtra("bus",u);
        startActivity(i);

    }
    public class GuardarRest extends AsyncTask<Fecha,Void,String>
    {
        @Override
        protected String doInBackground(Fecha... fechas) {
            final String url = DireccionIP.ip+"SrvFecha/crearFecha?" +
                    "fecha1={fS}&fecha2={fL}" +
                    "&infoBus={plca}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().
                    add(new StringHttpMessageConverter());
            Fecha f = fechas[0];
            String r = restTemplate.getForObject(url,String.class,
                    f.getHoraSalida(),
                    f.getHoraLlegada(),
                    u.getPlaca()
                    );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(CrearHistorico.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
