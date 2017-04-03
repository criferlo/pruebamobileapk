package com.hashitoapps.pruebanicepeople;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hashitoapps.pruebanicepeople.entities.Usuario;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    EditText nombre,apellido,edad,email,pass;
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrar_usuario);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/avenir.otf");


        nombre = (EditText) findViewById(R.id.txtnombre);
        apellido = (EditText) findViewById(R.id.txtapellido);
        edad = (EditText) findViewById(R.id.txtedad);
        email = (EditText) findViewById(R.id.txtemail);
        pass = (EditText) findViewById(R.id.txtpassword);

        nombre.setTypeface(face);
        apellido.setTypeface(face);
        edad.setTypeface(face);
        email.setTypeface(face);
        pass.setTypeface(face);

        contenedor= (LinearLayout) findViewById(R.id.contenedor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_registrar_usuario, menu);
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

    public void registrarUsuario(View view){

        Usuario usu = new Usuario(this);
        usu.setNombre(nombre.getText().toString());
        usu.setApellido(apellido.getText().toString());
        usu.setEdad(edad.getText().toString());
        usu.setEmail(email.getText().toString());
        usu.setPass(pass.getText().toString());
        usu.registrarUsuarioServidor(usu);

    }

    public void usuarioRegistrado(String mensaje){

        Snackbar.make(contenedor, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        finish();
    }


}
