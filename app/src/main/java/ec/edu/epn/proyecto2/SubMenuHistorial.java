package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import ec.edu.epn.proyecto2.Adaptador.AdaptadorFecha;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.sqlite.BusOH;
import ec.edu.epn.proyecto2.sqlite.FechaContract;

public class SubMenuHistorial extends AppCompatActivity {

    private ListView lvfecha;
    private Fecha datos[];
    private Bus u;
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
        u = (Bus)getIntent().getSerializableExtra("bus");
    }
    public void onResume(){
        super.onResume();
        String criterio; // muestra la primera parte del where
        criterio = FechaContract.Fecha.INFO_BUS + " = ?";
        String[] valoresCriterio; //muestra los datos del where
        valoresCriterio = new String[]{String.valueOf(u.getPlaca())};
        BusOH aoh = new BusOH(getApplicationContext());
        SQLiteDatabase sdb = aoh.getReadableDatabase();
        Cursor c = sdb.query(FechaContract.Fecha.NOMBRE_TABLA,
                        new String []{
                        FechaContract.Fecha.INFO_BUS,
                        FechaContract.Fecha.FECHA_SALIDA,
                        FechaContract.Fecha.FECHA_LLEGADA
                },
                criterio,valoresCriterio,null,null,null);
        datos = new Fecha[c.getCount()];

        for (int i =0; c.moveToNext(); i++) {
            datos[i] = new Fecha(
                    c.getString(2),
                    c.getString(1));
        }

        AdaptadorFecha ba = new AdaptadorFecha(this,datos);
        lvfecha.setAdapter(ba);
    }
    public void CrearHistorico(View view)
    {
        Intent i = new Intent(this, CrearHistorico.class);
        i.putExtra("bus",u);
        startActivity(i);

    }

}
