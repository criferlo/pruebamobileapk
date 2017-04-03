package com.hashitoapps.pruebanicepeople;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hashitoapps.pruebanicepeople.entities.Usuario;

public class LoginActivity extends AppCompatActivity {

    TextView email,password;
    EditText txtemail,txtpass;
    LinearLayout contenedor;
    Button buttonRegistrar,buttonIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/avenir.otf");

        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);

        email.setTypeface(face);
        password.setTypeface(face);

        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpass = (EditText) findViewById(R.id.txtpassword);
        contenedor= (LinearLayout) findViewById(R.id.contenedor);

        buttonIniciar = (Button) findViewById(R.id.entrar);
        buttonRegistrar = (Button) findViewById(R.id.registrate);


        txtemail.setTypeface(face);
        txtpass.setTypeface(face);

        buttonRegistrar.setTypeface(face);
        buttonIniciar.setTypeface(face);


        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(),RegistrarUsuarioActivity.class);
                startActivity(i);

            }
        });

    }

    public void iniciarSesion(){
        Usuario usu = new Usuario(this);
        usu.iniciarSesion(txtemail.getText().toString(),txtpass.getText().toString());
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


            Intent i = new Intent();
            i.setClass(getApplicationContext(),UsuariosListaActivity.class);
            startActivity(i);

        }else{
            Snackbar.make(contenedor, "No pudo iniciar, compruebe datos.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
