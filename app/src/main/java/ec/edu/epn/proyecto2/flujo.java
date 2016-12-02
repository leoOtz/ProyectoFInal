package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class flujo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frm_mapa);
    }

    public void irmenu (View view){
        Intent f = new Intent(this,MainActivity.class);
        startActivity(f);
    }
}
