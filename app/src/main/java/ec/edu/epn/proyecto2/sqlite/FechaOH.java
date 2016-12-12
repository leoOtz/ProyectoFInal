package ec.edu.epn.proyecto2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan on 12/12/2016.
 */

public class FechaOH extends SQLiteOpenHelper
{
    public FechaOH(Context context)
    {
        super(context, "admUsr.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(FechaContract.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

}
