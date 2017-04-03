package com.hashitoapps.pruebanicepeople.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hashitoapps.pruebanicepeople.R;
import com.hashitoapps.pruebanicepeople.entities.Ciudad;

import java.util.ArrayList;

/**
 * Created by practicante on 21/06/2016.
 */
public class CiudadesAdapter extends BaseAdapter{

    private Context  context;
    private ArrayList<Ciudad> ciudades;

    public CiudadesAdapter(Context context,ArrayList<Ciudad> ciudades){
        this.context = context;
        this.ciudades = ciudades;
    }

    @Override
    public int getCount() {
        return ciudades.size();
    }

    @Override
    public Object getItem(int position) {
        return ciudades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_ciudad, parent, false);

        TextView nombreciudad = (TextView) rowView.findViewById(R.id.txtNombre);
        Button btn = (Button) rowView.findViewById(R.id.btnVerClima);
        Button btnEliminar = (Button) rowView.findViewById(R.id.btnEliminar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ciudad c = new Ciudad(context);
                c.consultarClima(ciudades.get(pos).getNombre());
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ciudad c = new Ciudad(context);
                c.eliminarCiudad(ciudades.get(pos).getNombre());
            }
        });

        nombreciudad.setText(ciudades.get(position).getNombre());
        return rowView;

    }
}
