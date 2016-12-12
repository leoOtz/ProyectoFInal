package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ajustes extends AppCompatActivity {


    android.widget.TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

    }


    public void cambiar(View v)
    {
        TV = (TextView)findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/valuoldcaps.ttf";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        TV.setTypeface(TF);

    }

    public void cambiar2(View v)
    {
        TV = (TextView)findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/COMICATE.TTF";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        TV.setTypeface(TF);

    }
    public void cambiar3(View v)
    {
        TV = (TextView)findViewById(R.id.textView);                                                                                                    //medio de un ID
        String font_path = "fonts/sewer.ttf";                                                                                  //donde tiene que buscar ) de nuetra fuente
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        TV.setTypeface(TF);

    }


    public  void  cambiart (View v){

        TV = (TextView)findViewById(R.id.textView2);
        TV.setTextSize(30);

    }
    public  void  cambiart2 (View v){

        TV = (TextView)findViewById(R.id.textView2);
        TV.setTextSize(40);

    }
    public  void  cambiart3 (View v){

        TV = (TextView)findViewById(R.id.textView2);
        TV.setTextSize(50);

    }
    public void inicio(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
    }
