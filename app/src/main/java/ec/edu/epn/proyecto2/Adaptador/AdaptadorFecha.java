package ec.edu.epn.proyecto2.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ec.edu.epn.proyecto2.Objetos.Fecha;
import ec.edu.epn.proyecto2.R;

/**
 * Created by Juan on 12/12/2016.
 */

public class AdaptadorFecha extends ArrayAdapter
{
    private Fecha[] fecha;
    public AdaptadorFecha (Context context, Fecha[] fecha)
    {
        super(context,android.R.layout.simple_list_item_1,
                fecha);
        this.fecha=fecha;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.lv_bus_item,null);
        }
        ImageView iv = (ImageView)convertView.findViewById(
                R.id.imageView);
        TextView tv = (TextView)convertView.findViewById(
                R.id.txtNombre);
        TextView pl = (TextView)convertView.findViewById(
                R.id.txtplaca);
        String horaSalida = fecha[position].getHoraSalida();
        int idImg=R.drawable.img_reloj;

        iv.setImageResource(idImg);
        tv.setText("Hora Salida"+fecha[position].getHoraSalida());
        pl.setText("Hora Llegada"+fecha[position].getHoraLlegada());

        return convertView;
    }
}
