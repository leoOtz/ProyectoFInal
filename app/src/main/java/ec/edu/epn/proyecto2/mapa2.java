package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class mapa2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irmenu (View view){
        Intent f = new Intent(this,MainActivity.class);
        startActivity(f);
    }
}
