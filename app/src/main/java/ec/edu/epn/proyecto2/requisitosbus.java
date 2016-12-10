package ec.edu.epn.proyecto2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by USUARIO on 09/12/2016.
 */

public class requisitosbus extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requistosbuses);
    }

    public void regresarBus(View v){

        Intent i = new Intent(this,estadobuses.class);
        startActivity(i);
    }


}
