package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.sqlite.BusContract;
import ec.edu.epn.proyecto2.sqlite.BusOH;

public class EditarBus extends AppCompatActivity {

    EditText txtNombre, txtPlaca,txtPermiso;
    private Bus u;


    @Override
    protected void onCreate(Bundle datos) {
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
        BusOH edit = new BusOH(getApplicationContext());
        SQLiteDatabase bd = edit.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String criterio;
        String[] valoresCriterio;

        String nombre = txtNombre.toString();
        String placa = txtPlaca.toString();
        String permiso = txtPermiso.toString();




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
        Intent i = new Intent (this, GestionUnidades.class);
        startActivity(i);
    }
}
