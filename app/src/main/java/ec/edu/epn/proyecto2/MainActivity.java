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

    public void recaudo(View view){
        Intent recaudo = new Intent(this,Recaudo.class);
        startActivity(recaudo);
    }

    public void historialBuses(View View){
        Intent historialBuses = new Intent(this,HistorialBuses.class);
        startActivity(historialBuses);
    }
    public void estadoBuses(View View){
        Intent estado = new Intent(this,estadobuses.class);
        startActivity(estado);
    }
    public void gestionUnidades(View View){
        Intent gestionUnidades = new Intent(this,GestionUnidades.class);
        startActivity(gestionUnidades);
    }

    public void ajustes (View View)
    {
        Intent a = new Intent(this, ajustes.class);
        startActivity(a);
    }






}
