package ec.edu.epn.proyecto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CrearHistorico extends AppCompatActivity {

    EditText fecha1,fecha2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_historico);
        fecha1 = (EditText) findViewById(R.id.txtfecha1);
        fecha2 = (EditText)findViewById(R.id.txtfecha2);

    }
}
