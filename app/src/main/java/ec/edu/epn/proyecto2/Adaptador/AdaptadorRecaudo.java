package ec.edu.epn.proyecto2.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ec.edu.epn.proyecto2.Objetos.RecaudoVo;
import ec.edu.epn.proyecto2.R;

/**
 * Created by Juan on 13/02/2017.
 */

public class AdaptadorRecaudo extends ArrayAdapter
{
    private RecaudoVo[] recaudo;

    public AdaptadorRecaudo(Context context, RecaudoVo[] recaudo) {
        super(context,android.R.layout.simple_list_item_1,recaudo);
        this.recaudo = recaudo;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.lv_recaudo,null);
        }
        ImageView iv = (ImageView)convertView.findViewById(
                R.id.imageView);
        TextView tv = (TextView)convertView.findViewById(
                R.id.txtMonto);
        TextView pl = (TextView)convertView.findViewById(
                R.id.txtFecha);
        String horaSalida = recaudo[position].getRecaudo();
        int idImg=R.drawable.dw_recaudo;

        iv.setImageResource(idImg);
        tv.setText("RecaudoVo: "+recaudo[position].getRecaudo());
        pl.setText("Fecha: "+recaudo[position].getFecha());

        return convertView;
    }
}
