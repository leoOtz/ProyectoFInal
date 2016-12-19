package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ec.edu.epn.proyecto2.Objetos.Bus;
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

    }
}
