package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frminicio);

    }

    public void ir(View view){
        Intent i = new Intent(this,mapa_fiscalizador.class);
        startActivity(i);

    }

    public void flujounidades(View view){
        Intent flujo = new Intent(this,flujo.class);
        startActivity(flujo);
    }

    public void irUnidad(View View){
        Intent irUnidad = new Intent(this,crearbus.class);
        startActivity(irUnidad);
    }







}
