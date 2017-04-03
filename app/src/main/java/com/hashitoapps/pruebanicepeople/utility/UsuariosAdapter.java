package com.hashitoapps.pruebanicepeople.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hashitoapps.pruebanicepeople.R;
import com.hashitoapps.pruebanicepeople.entities.Ciudad;
import com.hashitoapps.pruebanicepeople.entities.Usuario;

import java.util.ArrayList;

/**
 * Created by practicante on 21/06/2016.
 */
public class UsuariosAdapter extends BaseAdapter{

    private Context  context;
    private ArrayList<Usuario> usuarios;

    public UsuariosAdapter(Context context, ArrayList<Usuario> usuarios){
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface face = Typeface.createFromAsset(context.getAssets(),"fonts/avenir.otf");

        final int pos = position;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_usuario, parent, false);

        TextView nombreciudad = (TextView) rowView.findViewById(R.id.txtNombre);
        nombreciudad.setTypeface(face);


        nombreciudad.setText(usuarios.get(position).getNombre());
        return rowView;

    }
}
