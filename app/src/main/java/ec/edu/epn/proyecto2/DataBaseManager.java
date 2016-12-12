package ec.edu.epn.proyecto2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TableLayout;

/**
 * Created by USUARIO on 11/12/2016.
 */

public class DataBaseManager {

    public static final String TABLE_NAME = "informacionbuses";

    public static final String IB_ID = "id";
    public static final String IB_NUMEROBUS = "numbus";
    public static final String IB_MARCA = "marca";
    public static final String IB_MODELO = "modelo";
    public static final String IB_ANIO = "anio";
    public static final String IB_PROPIETARIO = "nomPromietario";

    public static final String CREATE_TABLE = "create table" + TABLE_NAME + "("

            + IB_ID + "intenger primary Key autoincrement,"
            + IB_MARCA + "text not null,"
            + IB_NUMEROBUS + "text not null,"
            + IB_MODELO + "text not null,"
            + IB_ANIO + "text not null,"
            + IB_PROPIETARIO + "text),";


    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


    }




    private ContentValues generarContentValues(String marca , String numbus, String modelo, String anio , String propietario ){

        ContentValues valores = new ContentValues();
        valores.put(IB_MARCA, marca);
        valores.put(IB_NUMEROBUS, numbus);
        valores.put(IB_MODELO, modelo);
        valores.put(IB_ANIO, anio);
        valores.put(IB_PROPIETARIO,propietario);

        return valores;


    }
    public void insertar(String marca , String numbus, String modelo, String anio , String propietario ){


        db.insert(TABLE_NAME,IB_PROPIETARIO, generarContentValues(marca,numbus, modelo,anio,propietario));


    };


    public void elimininar (String marca){

        db.delete(TABLE_NAME,IB_MARCA+"=?",new String[]{marca});
    }


    public void modificarpropietario (String nmarca , String nmodelo,String nnumbus, String nanio , String npropietario){
        db.update(TABLE_NAME,generarContentValues(nmarca, nnumbus, nmodelo,nanio,npropietario),IB_NUMEROBUS+"=?",new String[]{nnumbus});

    }


    public Cursor cargarCursorBuses(){
        String[] columnas= new String[]{IB_ID,IB_MARCA,IB_NUMEROBUS,IB_MODELO,IB_ANIO,IB_PROPIETARIO};
      return   db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }
}
