package ec.edu.epn.proyecto2.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Juan on 12/12/2016.
 */

public class FechaContract
{
    public abstract class Fecha implements BaseColumns
    {
        public final static String NOMBRE_TABLA="FECHA";
        public final static String FECHA_SALIDA="FECHA_SALIDA";
        public final static String FECHA_LLEGADA="FECH_LLEGADA";
        public final static String INFO_BUS="INFO_BUS";

    }
    public final static String
            CREATE_TABLE =
            "CREATE TABLE " + Fecha.NOMBRE_TABLA+ " ("+
                    Fecha._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Fecha.FECHA_SALIDA + " TEXT,"+
                    Fecha.FECHA_LLEGADA + " TEXT,"+
                    Fecha.INFO_BUS + " TEXT,)";
}
