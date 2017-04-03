package com.hashitoapps.pruebanicepeople;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.hashitoapps.pruebanicepeople.entities.Ciudad;

public class CiudadActivity extends AppCompatActivity {

    EditText nombre,codigopais;
    Switch predeterminada;
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad);

        nombre = (EditText) findViewById(R.id.txtnombre);
        codigopais = (EditText) findViewById(R.id.txtcodigopais);
        predeterminada = (Switch) findViewById(R.id.predeterminada);
        contenedor = (LinearLayout) findViewById(R.id.contenedor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ciudad, menu);
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

    public void registrarCiudad(View view)
    {
        Ciudad ciu = new Ciudad(this);

        String p="";
        if(predeterminada.isChecked()){
            p="S";
        }else{
            p="N";
        }

        ciu.setNombre(nombre.getText().toString());
        ciu.setCodpais(codigopais.getText().toString());
        ciu.setPredeterminado(p);
        ciu.registrarCiudadServidor(ciu);

    }

    public void ciudadRegistrada(String nombre){
        Snackbar.make(contenedor, nombre, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
