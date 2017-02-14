package ec.edu.epn.proyecto2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by USUARIO on 09/12/2016.
 */

public class requisitosbus extends AppCompatActivity{

    android.widget.TextView TV;


    private Spinner cmblicencia;
    private Spinner cmbmatricula;
    private Spinner cmbllantas;
    private Spinner cmbluces;
    private Spinner cmbplumas;
    private Spinner cmbrtv;


    private SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requistosbuses);

        preferencias = getPreferences(Context.MODE_PRIVATE);



        cmblicencia = (Spinner) findViewById(R.id.cmblicencia);
        String[] datos = new String[]{"Vigente", "Caducada", "Certificado"};
        ArrayAdapter<String> aa = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datos);
        cmblicencia.setAdapter(aa);


        cmbmatricula = (Spinner) findViewById(R.id.cmbmatricula);
        String[] datosm = new String[]{"Vigente", "Caducada"};
        ArrayAdapter<String> bb = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosm);
        cmbmatricula.setAdapter(bb);

        cmbllantas = (Spinner) findViewById(R.id.cmbllantas);
        String[] datosll = new String[]{"Lisas", "Semi Nuevas", "Nuevas"};
        ArrayAdapter<String> ll = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosll);
        cmbllantas.setAdapter(ll);

        cmbluces = (Spinner) findViewById(R.id.cmbluces);
        String[] datosl = new String[]{"Buen Estado", "Defectuosas"};
        ArrayAdapter<String> l = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosl);
        cmbluces.setAdapter(l);

        cmbplumas = (Spinner) findViewById(R.id.cmbplumas);
        String[] datosp = new String[]{"Buen Estado", "Defectuosas"};
        ArrayAdapter<String> p = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosp);
        cmbplumas.setAdapter(p);



        cmbrtv = (Spinner) findViewById(R.id.cmbrtv);
        String[] datosr = new String[]{"Vigente", "Caducada"};
        ArrayAdapter<String> r = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        datosr);
        cmbrtv.setAdapter(r);





    }
    public void inicio(View v){

        Intent i = new Intent(this,estadobuses.class);
        startActivity(i);
    }


    public void guardar(View v) {

        SharedPreferences.Editor ed = preferencias.edit();


        ed.putInt("licencia", cmblicencia.getSelectedItemPosition());
        ed.putInt("matricula", cmbmatricula.getSelectedItemPosition());
        ed.putInt("llantas", cmbllantas.getSelectedItemPosition());
        ed.putInt("luces", cmbluces.getSelectedItemPosition());
        ed.putInt("plumas", cmbplumas.getSelectedItemPosition());
        ed.putInt("rtv", cmbrtv.getSelectedItemPosition());

        Toast.makeText(requisitosbus.this,
                "Cambios Guardados Exitosamente", Toast.LENGTH_LONG).show();

        ed.commit();



    }

    public void onResume(){


        super.onResume();
        cmblicencia.setSelection(preferencias.getInt("licencia",0));
        cmbmatricula.setSelection(preferencias.getInt("matricula",0));
        cmbllantas.setSelection(preferencias.getInt("llantas",0));
        cmbplumas.setSelection(preferencias.getInt("luces",0));
        cmbluces.setSelection(preferencias.getInt("plumas",0));
        cmbrtv.setSelection(preferencias.getInt("rtv",0));



    }


}
