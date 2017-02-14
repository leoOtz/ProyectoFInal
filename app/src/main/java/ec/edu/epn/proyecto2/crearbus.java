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
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;
import ec.edu.epn.proyecto2.sqlite.BusContract;
import ec.edu.epn.proyecto2.sqlite.BusOH;

public class crearbus extends AppCompatActivity {

    EditText txtNombre,txtPlaca,txtPermiso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearbus);

        txtNombre = (EditText) findViewById(R.id.txtnombreUnidad);
        txtPlaca = (EditText)findViewById(R.id.txtplacaUnidad);
        txtPermiso = (EditText)findViewById(R.id.txtpermisounidad);
    }
    public void guardar(View v)
    {
        //Inserción con SQLite
        /*
        BusOH boh = new BusOH(getApplicationContext());
        SQLiteDatabase sdb = boh.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put(BusContract.Bus.NOMBRE_IMAGEN,
                "bus");
        datos.put(BusContract.Bus.NOMBRE,
                txtNombre.getText().toString());
        datos.put(BusContract.Bus.PERMISO,
                txtPermiso.getText().toString());
        datos.put(BusContract.Bus.PLACA,
                txtPlaca.getText().toString());


        sdb.insert(BusContract.Bus.NOMBRE_TABLA,
                null,
                datos);
        sdb.close();
         */
        Bus b = new Bus();
        b.setNombre(txtNombre.getText().toString());
        b.setPlaca(txtPlaca.getText().toString());
        b.setPermiso(txtPermiso.getText().toString());
        b.setNombreImagen("bus");

        GuardarRest gr=new GuardarRest();
        gr.execute(b);
        Intent i = new Intent (this, GestionUnidades.class);
        startActivity(i);

    }
    public class GuardarRest extends AsyncTask<Bus,Void,String>
    {
        @Override
        protected String doInBackground(Bus... Buses) {
            final String url = DireccionIP.ip+"SrvBus/crearBus?" +
                            "nombre={n}&permiso={e}" +
                            "&placa={cl}&nombreImg={ni}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().
                    add(new StringHttpMessageConverter());
            Bus b = Buses[0];
            String r = restTemplate.getForObject(url,String.class,
                    b.getNombre(),
                    b.getPermiso(),
                    b.getPlaca(),
                    b.getNombreImagen());
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(crearbus.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
