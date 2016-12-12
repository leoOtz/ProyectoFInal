package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ec.edu.epn.proyecto2.Adaptador.AdaptadorFecha;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.sqlite.FechaContract;
import ec.edu.epn.proyecto2.sqlite.FechaOH;

public class SubMenuHistorial extends AppCompatActivity {

    private ListView lvfecha;
    private Fecha datos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu_historial);
        lvfecha = (ListView)findViewById(R.id.lvFecha);
        lvfecha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

            }
        });
    }
    public void onResume(){
        super.onResume();
        FechaOH aoh = new FechaOH(getApplicationContext());
        SQLiteDatabase sdb = aoh.getReadableDatabase();
        Cursor c = sdb.query(FechaContract.Fecha.NOMBRE_TABLA,
                new String []{
                        FechaContract.Fecha.FECHA_LLEGADA,
                        FechaContract.Fecha.FECHA_SALIDA,
                        FechaContract.Fecha.INFO_BUS
                },null,null,null,null,null);
        datos = new Fecha[c.getCount()];

        for (int i =0; c.moveToNext(); i++) {
            datos[i] = new Fecha(
                    c.getString(1),
                    c.getString(0));
        }

        AdaptadorFecha ba = new AdaptadorFecha(this,datos);
        lvfecha.setAdapter(ba);
    }
    public void inicio(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

}
