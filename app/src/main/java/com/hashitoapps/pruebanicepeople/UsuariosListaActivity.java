package com.hashitoapps.pruebanicepeople;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hashitoapps.pruebanicepeople.entities.Ciudad;
import com.hashitoapps.pruebanicepeople.entities.Usuario;
import com.hashitoapps.pruebanicepeople.utility.CiudadesAdapter;
import com.hashitoapps.pruebanicepeople.utility.UsuariosAdapter;

import java.util.ArrayList;

public class UsuariosListaActivity extends AppCompatActivity {


    ListView lstCiudades;
    LinearLayout contenedor;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_lista);

        contenedor = (LinearLayout) findViewById(R.id.contenedor);
        lstCiudades  = (ListView) findViewById(R.id.lstUsuarios);
        salir = (Button) findViewById(R.id.salir);
        consultarCiudades();


    }

    public void consultarCiudades(){
        Usuario ciu = new Usuario(this);
        ciu.consultarUsuarios();
    }

    public void setearLista(ArrayList<Usuario> lista){
        UsuariosAdapter adapter = new UsuariosAdapter(this,lista);
        lstCiudades.setAdapter(adapter);
    }

    public void salir(View view){
        finish();
    }


}
