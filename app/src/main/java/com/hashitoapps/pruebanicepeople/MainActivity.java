package com.hashitoapps.pruebanicepeople;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.iniciarSesion) {

            Intent i = new Intent();
            i.setClass(this,IniciarSesionActivity.class);
            startActivity(i);

        }else if(id == R.id.registrarUsuario){
            Intent i = new Intent();
            i.setClass(this,RegistrarUsuarioActivity.class);
            startActivity(i);
        }else if(id == R.id.registrarCiudad){
            Intent i = new Intent();
            i.setClass(this,CiudadActivity.class);
            startActivity(i);
        }else if(id == R.id.verCiudades){
            Intent i = new Intent();
            i.setClass(this,CiudadListaActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
