package ec.edu.epn.proyecto2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class listarbuses extends AppCompatActivity {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listabuses);

        manager.insertar("Hino", "104", "AK", "2016", "PoloParedes");


        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.ListView);

        String[] from = new String[]{manager.IB_MODELO, manager.IB_MARCA};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
        cursor = manager.cargarCursorBuses();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        lista.setAdapter(adapter);


    }
}
