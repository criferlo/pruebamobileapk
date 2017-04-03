package com.hashitoapps.pruebanicepeople.utility;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by practicante on 21/06/2016.
 */
public class RestClient {

    //private static final String BASE_URL="http://@@/vmas/vmas/sincronizacion/";
    private static final String BASE_URL="http://@@/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getWithoutServer(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(url,params,responseHandler);
    }

    public static void get(String server,String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(getAbsoluteUrl(server,url),params,responseHandler);
    }

    public static void post(String server,String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        String k = getAbsoluteUrl(server,url);
        client.post(k, params, responseHandler);
    }

    public static void imagenGet(String url,FileAsyncHttpResponseHandler responseHandler){
        client.get(url, responseHandler);
    }



    private static String getAbsoluteUrl(String server,String relativeUrl) {
        String cad = BASE_URL.replace("@@",server);
        return cad + relativeUrl;
    }
}
