package com.hashitoapps.pruebanicepeople.utility;

import android.content.Context;

/**
 * Created by practicante on 21/06/2016.
 */
public class Constantes {

    public static String obtenerServidorServicios(){

        String s= "192.168.0.14/api_nicepeople";
        return s;
    }

    public static String obtenerServicioClima(){
        //String s= "http://api.openweathermap.org/data/2.5/weather?q=London&APPID=6a1a7cb78d60a293df6fcea6aba22bcd";
        String s= "http://api.openweathermap.org/data/2.5/weather";
        return s;

    }

    public static String appid(){
        String s= "6a1a7cb78d60a293df6fcea6aba22bcd";
        return s;
    }
}
