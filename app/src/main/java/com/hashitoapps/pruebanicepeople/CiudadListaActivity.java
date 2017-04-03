package com.hashitoapps.pruebanicepeople;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hashitoapps.pruebanicepeople.entities.Ciudad;
import com.hashitoapps.pruebanicepeople.utility.CiudadesAdapter;

import java.util.ArrayList;
import java.util.List;

public class CiudadListaActivity extends AppCompatActivity {

    ListView lstCiudades;
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad_lista);

        contenedor = (LinearLayout) findViewById(R.id.contenedor);
        lstCiudades  = (ListView) findViewById(R.id.lstCiudades);
        consultarCiudades();



    }

    public void consultarCiudades(){
        Ciudad ciu = new Ciudad(this);
        ciu.consultarCiudades();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ciudad_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setearLista(ArrayList<Ciudad> lista){
        CiudadesAdapter  adapter = new CiudadesAdapter(this,lista);
        lstCiudades.setAdapter(adapter);
    }

    public void eliminarCiudadRespuesta(){
        Snackbar.make(contenedor, "Eliminado correctamente", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        consultarCiudades();
    }

    public void enviarClima(String clima){
        Snackbar.make(contenedor, "Clima:"+clima, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
