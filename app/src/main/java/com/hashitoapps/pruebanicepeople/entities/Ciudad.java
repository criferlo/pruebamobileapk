package com.hashitoapps.pruebanicepeople.entities;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hashitoapps.pruebanicepeople.CiudadActivity;
import com.hashitoapps.pruebanicepeople.CiudadListaActivity;
import com.hashitoapps.pruebanicepeople.RegistrarUsuarioActivity;
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
public class Ciudad {

    private int id;
    private String nombre;
    private String codpais;
    private String predeterminado;
    private ProgressDialog prgDialog;
    private Context context;

    public Ciudad(Context context){
        this.context = context;
        prgDialog = new ProgressDialog(context);
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

    public String getCodpais() {
        return codpais;
    }

    public void setCodpais(String codpais) {
        this.codpais = codpais;
    }

    public String getPredeterminado() {
        return predeterminado;
    }

    public void setPredeterminado(String predeterminado) {
        this.predeterminado = predeterminado;
    }

    public void registrarCiudadServidor(Ciudad usu){
        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> var_usuario = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> var = new HashMap<String, String>();
        var.put("nombre",usu.getNombre());
        var.put("codpais",usu.getCodpais());
        var.put("predeterminada",usu.getPredeterminado());
        var_usuario.add(var);
        Gson go = new GsonBuilder().create();
        params.put("var_ciudad", go.toJson(var_usuario));

        RestClient.post(Constantes.obtenerServidorServicios(), "nicepeople/registrarCiudad", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                prgDialog.setMessage("Registrando ciudad...");
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

    public void consultarCiudades(){

        RestClient.get(Constantes.obtenerServidorServicios(), "nicepeople/consultarCiudades", null, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                prgDialog.setMessage("Consultando ciudades...");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    prgDialog.hide();

                    //if (response.length() > 0) {
                    enviarCiudades(response);
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

    private void enviarCiudades(JSONArray response) throws Exception{

        ArrayList<Ciudad> res = new ArrayList<>();

        for(int i=0;i<response.length();i++){
            JSONObject o = response.getJSONObject(i);

            Ciudad c = new Ciudad(context);
            c.setNombre(o.getString("nombre"));
            c.setPredeterminado(o.getString("predeterminada"));
            c.setCodpais(o.getString("codpais"));
            res.add(c);
        }

        CiudadListaActivity d = (CiudadListaActivity)context;
        d.setearLista(res);

    }

    public void eliminarCiudad(String nombre){

        RequestParams params = new RequestParams();
        ArrayList<HashMap<String, String>> var_usuario = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> var = new HashMap<String, String>();
        var.put("nombre",nombre);
        var_usuario.add(var);
        Gson go = new GsonBuilder().create();
        params.put("var_ciudad", go.toJson(var_usuario));

        RestClient.post(Constantes.obtenerServidorServicios(), "nicepeople/eliminarCiudad", params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                prgDialog.setMessage("Eliminar ciudad..");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {

                    prgDialog.hide();

                    if (response.length() > 0) {
                        eliminarCiudadRespuesta();
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

    public void consultarClima(String nombre){
        RequestParams params = new RequestParams();
        params.put("q",nombre);
        params.put("APPID",Constantes.appid());

        RestClient.getWithoutServer(Constantes.obtenerServicioClima(), params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                prgDialog.setMessage("Consultando clima");
                prgDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    prgDialog.hide();
                    JSONObject weather = response.getJSONObject("main");
                    String clima = weather.getString("temp");
                    enviarClima(clima);

                } catch (Exception ex) {
                    Log.d("error", ex.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                prgDialog.hide();
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void enviarClima(String clima) {

        CiudadListaActivity d = (CiudadListaActivity)context;
        d.enviarClima(clima);

    }

    private void eliminarCiudadRespuesta() {

        CiudadListaActivity d = (CiudadListaActivity)context;
        d.eliminarCiudadRespuesta();
    }

    private void actualizarMensaje(String mensaje) {

        if(context instanceof CiudadActivity){
            CiudadActivity d = (CiudadActivity)context;
            d.ciudadRegistrada(mensaje);
        }
    }

}
