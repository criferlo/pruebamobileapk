package com.hashitoapps.pruebanicepeople.entities;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hashitoapps.pruebanicepeople.CiudadListaActivity;
import com.hashitoapps.pruebanicepeople.IniciarSesionActivity;
import com.hashitoapps.pruebanicepeople.LoginActivity;
import com.hashitoapps.pruebanicepeople.RegistrarUsuarioActivity;
import com.hashitoapps.pruebanicepeople.UsuariosListaActivity;
import com.hashitoapps.pruebanicepeople.utility.Constantes;
import com.hashitoapps.pruebanicepeople.utility.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by practicante on 21/06/2016.
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private String edad;
    private String email;
    private String pass;
    private ProgressDialog prgDialog;
    private Context context;

    public Usuario(Context context){
        prgDialog = new ProgressDialog(context);
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void registrarUsuarioServidor(Usuario usu){
        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> var_usuario = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> var = new HashMap<String, String>();
        var.put("nombre",usu.getNombre());
        var.put("apellido",usu.getApellido());
        var.put("edad",usu.getEdad());
        var.put("email",usu.getEmail());
        var.put("pass",usu.getPass());
        var_usuario.add(var);
        Gson go = new GsonBuilder().create();
        params.put("var_usuario", go.toJson(var_usuario));

        RestClient.post(Constantes.obtenerServidorServicios(),"nicepeople/registrarUsuario",params,new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                prgDialog.setMessage("Registrando usuario...");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    prgDialog.hide();

                    if (response.length() > 0) {
                        actualizarMensaje("Registrado correctamente");
                    }

                } catch (Exception ex) {
                    Log.d("error", ex.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {

                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }

    public void iniciarSesion(String email,String pass){
        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> var_usuario = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> var = new HashMap<String, String>();
        var.put("email",email);
        var.put("pass",pass);
        var_usuario.add(var);
        Gson go = new GsonBuilder().create();
        params.put("var_usuario", go.toJson(var_usuario));

        RestClient.post(Constantes.obtenerServidorServicios(),"nicePeople/iniciarSesion",params,new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                prgDialog.setMessage("Iniciando sesiòn");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    prgDialog.hide();

                    if (response.length() > 0) {
                        actualizarMensaje2(response.getJSONObject(0).getString("nombre"));
                    }

                } catch (Exception ex) {
                    Log.d("error", ex.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {

                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }


    private void actualizarMensaje(String mensaje) {

        if(context instanceof RegistrarUsuarioActivity){
            RegistrarUsuarioActivity d = (RegistrarUsuarioActivity)context;
            d.usuarioRegistrado(mensaje);
        }
    }

    private void actualizarMensaje2(String nombre) {

        if(context instanceof LoginActivity){
            LoginActivity d = (LoginActivity)context;
            d.inicioSesion(nombre);
        }
    }

    public void consultarUsuarios(){

        RestClient.get(Constantes.obtenerServidorServicios(), "nicepeople/consultarUsuarios", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                prgDialog.setMessage("Consultando usuarios...");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    prgDialog.hide();

                    //if (response.length() > 0) {
                    enviarUsuarios(response);
                    //}

                } catch (Exception ex) {
                    Log.d("error", ex.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {

                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }


    private void enviarUsuarios(JSONArray response) throws Exception{

        ArrayList<Usuario> res = new ArrayList<>();

        for(int i=0;i<response.length();i++){
            JSONObject o = response.getJSONObject(i);

            Usuario c = new Usuario(context);
            c.setNombre(o.getString("nombre"));
            res.add(c);
        }

        UsuariosListaActivity d = (UsuariosListaActivity)context;
        d.setearLista(res);

    }
}
