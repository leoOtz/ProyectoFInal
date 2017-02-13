package ec.edu.epn.proyecto2.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ec.edu.epn.proyecto2.Objetos.Recaudo;
import ec.edu.epn.proyecto2.R;

/**
 * Created by Juan on 13/02/2017.
 */

public class AdaptadorRecaudo extends ArrayAdapter
{
    private Recaudo[] recaudo;

    public AdaptadorRecaudo(Context context, Recaudo[] recaudo) {
        super(context,android.R.layout.simple_list_item_1,recaudo);
        this.recaudo = recaudo;
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
        String horaSalida = recaudo[position].getRecaudo();
        int idImg=R.drawable.dw_recaudo;

        iv.setImageResource(idImg);
        tv.setText("Recaudo: "+recaudo[position].getRecaudo());
        pl.setText("Fecha: "+recaudo[position].getFecha());

        return convertView;
    }
}
