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

public class EditarBus extends AppCompatActivity {

    EditText txtNombre, txtPlaca,txtPermiso;
    private Bus u;


    @Override
    protected void onCreate(Bundle datos)
    {
        super.onCreate(datos);
        setContentView(R.layout.activity_editar_bus);
        txtPlaca = (EditText)findViewById(R.id.txtplacaUnidad);
        txtNombre = (EditText)findViewById(R.id.txtnombreUnidad);
        txtPermiso = (EditText)findViewById(R.id.txtpermisounidad);
        u = (Bus)getIntent().getSerializableExtra("bus");
        txtNombre.setText(u.getNombre());
        txtPermiso.setText(u.getPermiso());
        txtPlaca.setText(u.getPlaca());


    }

    @Override
    protected void onPause()
    {
        super.onPause();
        this.finish();
    }

    public void guardar(View v)
    {
        //Parte SQLite
        /*
        BusOH edit = new BusOH(getApplicationContext());
        SQLiteDatabase bd = edit.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String criterio;
        String[] valoresCriterio;

        String nombre = txtNombre.getText().toString();
        String placa = txtPlaca.getText().toString();
        String permiso = txtPermiso.getText().toString();
        // actualizamos con los nuevos datos, la información cambiada
        registro.put(BusContract.Bus.NOMBRE, nombre);
        registro.put(BusContract.Bus.PLACA, placa);
        registro.put(BusContract.Bus.PERMISO, permiso);

        criterio = BusContract.Bus.NOMBRE + " = ?";
        valoresCriterio = new String[]{String.valueOf(u.getNombre())};

        int count = bd.update( BusContract.Bus.NOMBRE_TABLA, registro, criterio ,valoresCriterio);


        bd.close();

        if (count == 1)

            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT).show();

        else

            Toast.makeText(this, "No existe usuario",

                    Toast.LENGTH_SHORT).show();

           */
        Bus aB = new Bus(txtPlaca.getText().toString()
                        ,txtPermiso.getText().toString()
                        ,txtNombre.getText().toString(),
                         "bus");
        EditarRest eB = new EditarRest();
        eB.execute(aB);
        Intent i = new Intent (this, GestionUnidades.class);
        startActivity(i);
    }
    public class EditarRest extends AsyncTask<Bus,Void,String>
    {
        @Override
        protected String doInBackground(Bus... Buses) {
            final String url = DireccionIP.ip+"SrvBus/actualizarBus?"+
                    "placa={p}&placaN={pN}" +
                    "&nombre={n}&permiso={pE}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Bus b = Buses[0];
            String r = restTemplate.getForObject(url,String.class,
                    u.getPlaca(),
                    b.getPlaca(),
                    b.getNombre(),
                    b.getPermiso());
            return r;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(EditarBus.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
