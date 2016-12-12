package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        Intent i = new Intent (this, GestionUnidades.class);
        startActivity(i);

    }
}
