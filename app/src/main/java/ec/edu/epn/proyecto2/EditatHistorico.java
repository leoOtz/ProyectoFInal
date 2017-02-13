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

public class EditatHistorico extends AppCompatActivity {

    EditText fecha1,fecha2;
    Bus u;
    Fecha f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editat_historico);
        fecha1 = (EditText) findViewById(R.id.txtfecha1);
        fecha2 = (EditText)findViewById(R.id.txtfecha2);
        u =(Bus) getIntent().getSerializableExtra("bus");
        f = (Fecha) getIntent().getSerializableExtra("fecha");
        fecha1.setText(f.getHoraSalida());
        fecha2.setText(f.getHoraLlegada());

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

        Intent i = new Intent (this, SubMenuHistorial.class);
        i.putExtra("bus",u);
        startActivity(i);
        */
        EditarHis eH = new EditarHis();
        Fecha fA= new Fecha();
        fA.setHoraSalida(fecha1.getText().toString());
        fA.setHoraLlegada(fecha2.getText().toString());
        eH.execute(fA);
        Intent i = new Intent (this, SubMenuHistorial.class);
        i.putExtra("bus",u);
        startActivity(i);
    }

    //Meotod RES actualizar
    public class EditarHis extends AsyncTask<Fecha,Void,String>
    {
        @Override
        protected String doInBackground(Fecha... Fechas) {
            final String url = DireccionIP.ip+"SrvFecha/editarFecha?" +
                    "fecha1={f1}&fecha2={f2}" +
                    "&info={plca}" +
                    "&fecha1N={f1N}&fecha2N={f2N}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Fecha fN = Fechas[0];
            String r = restTemplate.getForObject(url,String.class,
                    f.getHoraSalida(),
                    f.getHoraLlegada(),
                    u.getPlaca(),
                    fN.getHoraSalida(),
                    fN.getHoraLlegada());
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(EditatHistorico.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
