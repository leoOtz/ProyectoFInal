package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PantallaCarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i = new Intent(PantallaCarga.this, MainActivity.class );
                startActivity(i);
            }
        },2000);
    }
}
