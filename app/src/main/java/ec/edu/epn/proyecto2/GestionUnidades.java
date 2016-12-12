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



import ec.edu.epn.proyecto2.Adaptador.BusAdapter;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.sqlite.BusContract;
import ec.edu.epn.proyecto2.sqlite.BusOH;

public class GestionUnidades extends AppCompatActivity {

    private ListView lvUnidades;
    private Bus datos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_unidades);

        lvUnidades = (ListView)findViewById(R.id.lvUnidades);
        lvUnidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Bus bus = datos[posicion];
                Toast.makeText(GestionUnidades.this,"Bus: " +
                        bus.getNombre(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(GestionUnidades.this,EditarBus.class);
                i.putExtra("bus", bus);
                startActivity(i);
            }
        });
    }
    public void onResume(){
        super.onResume();
        BusOH aoh = new BusOH(getApplicationContext());
        SQLiteDatabase sdb = aoh.getReadableDatabase();
        Cursor c = sdb.query(BusContract.Bus.NOMBRE_TABLA,
                new String []{
                        BusContract.Bus.NOMBRE_IMAGEN,
                        BusContract.Bus.PLACA,
                        BusContract.Bus.NOMBRE,
                        BusContract.Bus.PERMISO
                },
                null,null,null,null,null);
        datos = new Bus[c.getCount()];

        for (int i =0; c.moveToNext(); i++) {
            datos[i] = new Bus(
                    c.getString(1),
                    c.getString(3),
                    c.getString(2),
                    c.getString(0));
        }

        BusAdapter ba = new BusAdapter(this,datos);
        lvUnidades.setAdapter(ba);
    }
    public void abrirCrearBus (View v) {
        Intent i = new Intent(this,crearbus.class);
        startActivity(i);
    }
}
