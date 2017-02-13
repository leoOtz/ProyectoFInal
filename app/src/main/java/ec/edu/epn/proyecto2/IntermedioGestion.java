package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import ec.edu.epn.proyecto2.sqlite.BusContract;
import ec.edu.epn.proyecto2.sqlite.BusOH;

import static ec.edu.epn.proyecto2.R.layout.frminicio;

public class IntermedioGestion extends AppCompatActivity {

    private Bus u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio_gestion);
        u = (Bus)getIntent().getSerializableExtra("bus");
    }
    public void editarBus(View view)
    {
        Intent i = new Intent(this, EditarBus.class);
        i.putExtra("bus", u);
        Log.v("bus", u.getPlaca());
        startActivity(i);
    }
    public void eliminar(View view)
    {
        /*
        BusOH edit = new BusOH(getApplicationContext());
        SQLiteDatabase bd = edit.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String criterio;
        String[] valoresCriterio;

        criterio = BusContract.Bus.NOMBRE + " = ?";
        valoresCriterio = new String[]{String.valueOf(u.getNombre())};

       bd.delete(BusContract.Bus.NOMBRE_TABLA,criterio,valoresCriterio);
       */

        EliminarRest eB =new EliminarRest();
        eB.execute(u);

        Intent i = new Intent(this, GestionUnidades.class);
        startActivity(i);
    }
    public class EliminarRest extends AsyncTask<Bus,Void,String>
    {
        @Override
        protected String doInBackground(Bus... Buses) {
            final String url = DireccionIP.ip+"SrvBus/eliminarBus?"+
                    "placa={p}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Bus b = Buses[0];
            String r = restTemplate.getForObject(url,String.class,
                    b.getPlaca()
                    );
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(IntermedioGestion.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
