package ec.edu.epn.proyecto2.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import ec.edu.epn.proyecto2.Objetos.Bus;
import ec.edu.epn.proyecto2.R;

/**
 * Created by Juan on 12/12/2016.
 */

public class BusAdapter2  extends ArrayAdapter
{
    private Bus[] buses;
    public BusAdapter2 (Context context, Bus[] buses){
        super(context,android.R.layout.simple_list_item_1,
                buses);
        this.buses=buses;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.lv_bus_item_2,null);
        }
     //   Switch sw =(Switch) convertView.findViewById(                R.id.BtnSwitch);
        ImageView iv = (ImageView)convertView.findViewById(
                R.id.imageView);
        TextView tv = (TextView)convertView.findViewById(
                R.id.txtNombre);
        TextView pl = (TextView)convertView.findViewById(
                R.id.txtplaca);
        String nombreImagen = buses[position].getNombreImagen();
        int idImg=R.drawable.ic_action_name;
        if (nombreImagen.equals("bus"))
        {
            idImg=R.drawable.ic_action_name;
        }
        iv.setImageResource(idImg);
        tv.setText(buses[position].getNombre());
        pl.setText(buses[position].getPlaca());

        return convertView;
    }
}
