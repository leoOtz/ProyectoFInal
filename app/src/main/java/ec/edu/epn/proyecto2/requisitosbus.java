package ec.edu.epn.proyecto2;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.Toast;

/**
 * Created by USUARIO on 09/12/2016.
 */

public class requisitosbus extends AppCompatActivity{

    CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requistosbuses);
        ch1=(CheckBox)findViewById(R.id.checkbox_requi1);
        ch2=(CheckBox)findViewById(R.id.checkbox_requi2);
        ch3=(CheckBox)findViewById(R.id.checkbox_requi3);
        ch4=(CheckBox)findViewById(R.id.checkbox_requi4);
        ch5=(CheckBox)findViewById(R.id.checkbox_requi5);
        ch6=(CheckBox)findViewById(R.id.checkbox_requi6);
        ch7=(CheckBox)findViewById(R.id.checkbox_requi7);
ch1.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        //is chkIos checked?
        if (((CheckBox) v).isChecked()) {
            Toast.makeText(requisitosbus.this,
                    "Bro, try Android :)", Toast.LENGTH_LONG).show();
        }

    }
});
        ch1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?


            }
        });
        ch2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(requisitosbus.this,
                            "Habilitado para manejar", Toast.LENGTH_LONG).show();
                }

            }
        });
        ch3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        ch4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ch5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ch6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        ch7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void regresarBus(View v){

        Intent i = new Intent(this,estadobuses.class);
        startActivity(i);
    }


}
