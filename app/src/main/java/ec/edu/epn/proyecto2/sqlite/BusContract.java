package ec.edu.epn.proyecto2.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Juan on 11/12/2016.
 */

public class BusContract
{
    public abstract class Bus implements BaseColumns
    {
        public final static String NOMBRE_TABLA="Bus";
        public final static String NOMBRE_IMAGEN="NOMBRE_IMAGEN";
        public final static String NOMBRE="NOMBRE";
        public final static String PLACA="PLACA";
        public final static String PERMISO="PERMISO";
    }
    public final static String
            CREATE_TABLE =
            "CREATE TABLE " + Bus.NOMBRE_TABLA+ " ("+
                    Bus._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Bus.NOMBRE_IMAGEN + " TEXT,"+
                    Bus.NOMBRE + " TEXT,"+
                    Bus.PLACA + " TEXT,"+
                    Bus.PERMISO + " TEXT)";
}
