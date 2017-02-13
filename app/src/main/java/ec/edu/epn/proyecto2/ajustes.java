package ec.edu.epn.proyecto2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ajustes extends AppCompatActivity {


    android.widget.TextView TV;
    private Spinner cmbNotificacion;
    private Spinner cmbSonido;
    private Spinner cmbLed;
    private Spinner cmbFlash;


    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        preferencias = getPreferences(Context.MODE_PRIVATE);

        cmbNotificacion = (Spinner) findViewById(R.id.cmbNotificacion);
        String[] datos = new String[]{"Vibracion", "Sonido", "Vibracion y Sonido"};
        ArrayAdapter<String> aa = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datos);
        cmbNotificacion.setAdapter(aa);


        cmbSonido = (Spinner) findViewById(R.id.cmbSonido);
        String[] dato = new String[]{"Big_Easy", "BirdLoop", "Cairo", "Bollywod", "Club_Cubano"};
        ArrayAdapter<String> bb = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        dato);
        cmbSonido.setAdapter(bb);


        cmbLed = (Spinner) findViewById(R.id.cmbLed);
        String[] datosled = new String[]{"Azul", "Roja", "Amarilla", "Cyan"};
        ArrayAdapter<String> cc = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosled);
        cmbLed.setAdapter(cc);


        cmbFlash = (Spinner) findViewById(R.id.cmbFlash);
        String[] datosflash = new String[]{"Encendido", "Apagado"};
        ArrayAdapter<String> dd = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosflash);
        cmbFlash.setAdapter(dd);

    }


    public void cambiar(View v) {
        TV = (TextView) findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/valuoldcaps.ttf";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(), font_path);
        TV.setTypeface(TF);

    }

    public void cambiar2(View v) {
        TV = (TextView) findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/COMICATE.TTF";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(), font_path);
        TV.setTypeface(TF);

    }

    public void cambiar3(View v) {
        TV = (TextView) findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/sewer.ttf";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(), font_path);
        TV.setTypeface(TF);

    }


    public void cambiart(View v) {

        TV = (TextView) findViewById(R.id.textView2);
        TV.setTextSize(30);

    }

    public void cambiart2(View v) {

        TV = (TextView) findViewById(R.id.textView2);
        TV.setTextSize(40);

    }

    public void cambiart3(View v) {

        TV = (TextView) findViewById(R.id.textView2);
        TV.setTextSize(50);

    }

    public void inicio(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void guardar(View v) {

        SharedPreferences.Editor ed = preferencias.edit();
        ed.putInt("Notificacion", cmbNotificacion.getSelectedItemPosition());
        ed.putInt("Sonido", cmbSonido.getSelectedItemPosition());
        ed.putInt("Led", cmbLed.getSelectedItemPosition());
        ed.putInt("Flash", cmbFlash.getSelectedItemPosition());
        Toast.makeText(ajustes.this,
                "Cambios Guardados Exitosamente", Toast.LENGTH_LONG).show();

        ed.commit();

    }
    public void onResume(){

        super.onResume();
        cmbNotificacion.setSelection(preferencias.getInt("Notificacion",0));
        cmbSonido.setSelection(preferencias.getInt("Sonido",0));
        cmbLed.setSelection(preferencias.getInt("Led",0));
        cmbFlash.setSelection(preferencias.getInt("Flash",0));
    }

}

