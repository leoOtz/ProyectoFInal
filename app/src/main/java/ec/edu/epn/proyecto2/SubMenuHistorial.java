package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ec.edu.epn.proyecto2.Adaptador.AdaptadorFecha;
import ec.edu.epn.proyecto2.Adaptador.BusAdapter;
import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.Utilitarios.DireccionIP;
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
        u = (Bus)getIntent().getSerializableExtra("bus");
        lvfecha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Fecha fecha = datos[posicion];
                Toast.makeText(SubMenuHistorial.this,"fecha: " +u.getNombre(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(SubMenuHistorial.this,SubHistorialFecha.class);
                i.putExtra("bus", u);
                i.putExtra("fecha", fecha);
                startActivity(i);
            }
        });

    }
    public void onResume(){
        super.onResume();
        /*
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
        */

        ConsultarFecha cF = new ConsultarFecha();
        cF.execute();
    }
    public void CrearHistorico(View view)
    {
        Intent i = new Intent(this, CrearHistorico.class);
        i.putExtra("bus",u);
        startActivity(i);

    }
    public void inicio(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public class ConsultarFecha extends AsyncTask<Void,Void,Fecha[]>
    {
        @Override
        protected Fecha[] doInBackground(Void... Void) {
            final String url = DireccionIP.ip+"SrvFecha/consultarFecha?info={plca}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Fecha [] r= restTemplate.getForObject(url,Fecha[].class,
                    u.getPlaca());
            return r;
        }
        @Override
        protected void onPostExecute(Fecha[] b) {
            super.onPostExecute(b);
            datos =b;
            AdaptadorFecha ba = new AdaptadorFecha(SubMenuHistorial.this,datos);
            lvfecha.setAdapter(ba);
            //  Toast.makeText(GestionUnidades.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
