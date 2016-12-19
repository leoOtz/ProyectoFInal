package ec.edu.epn.proyecto2.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Juan on 11/12/2016.
 */

public class BusOH extends SQLiteOpenHelper
{
    public BusOH(Context context)
    {
        super(context, "admUsr.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(BusContract.CREATE_TABLE);
        db.execSQL(FechaContract.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL(BusContract.SQL_DELETE_ENTRIES);
        db.execSQL(FechaContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
