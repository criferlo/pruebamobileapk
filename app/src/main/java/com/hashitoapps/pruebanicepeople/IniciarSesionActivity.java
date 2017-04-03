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

import com.hashitoapps.pruebanicepeople.entities.Usuario;

public class IniciarSesionActivity extends AppCompatActivity {

    EditText email,pass;
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


        email = (EditText) findViewById(R.id.txtemail);
        pass = (EditText) findViewById(R.id.txtpassword);

        contenedor= (LinearLayout) findViewById(R.id.contenedor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_iniciar_sesion, menu);
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

    public void iniciarSesion(View view){
        Usuario usu = new Usuario(this);
        usu.iniciarSesion(email.getText().toString(),pass.getText().toString());
    }


    public void inicioSesion(String nombre){
        //Snackbar.make(contenedor, mensaje, Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();

        if(!nombre.equals("")){
            SharedPreferences prefs = this.getSharedPreferences("hashito", Context.MODE_PRIVATE);
            prefs.edit().putString("nombre", nombre).apply();
            prefs.edit().commit();
            Snackbar.make(contenedor, "Iniciada sesiòn como:" + nombre, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else{
            Snackbar.make(contenedor, "No pudo iniciar, compruebe datos.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
