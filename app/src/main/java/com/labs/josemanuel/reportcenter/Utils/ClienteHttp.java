package com.labs.josemanuel.reportcenter.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.labs.josemanuel.reportcenter.ui.AdaptadorPropuestas;
import com.labs.josemanuel.reportcenter.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Miguel on 5/22/2016.
 */
public class ClienteHttp {
    private static String mUrl;

    String tokenURL= "/rest/session/token";
    Context mContext;
    //Data
    //Aplicamos el patrón Singleton en el uso de Volley para generar una única instancia de una RequestQueue, o cola de peticiones
    VolleySingleton mVolleySingleton;
    RequestQueue mRequestQueue;

    public ClienteHttp(String url,Context context){
        mUrl= url;
        mContext=context;

    }
    public void initiate(){
        //Data
        mVolleySingleton = VolleySingleton.getInstance(mContext); //Recogemos una instancia de Volley
        mRequestQueue = mVolleySingleton.getRequestQueue(); //Recogemos una cola de peticiones Http
    }
    /**
     * @param tag
     * Nullable, tag para identificar la petición en la cola.
     * @param req
     * Petición a procesar por la librería Volley
     *
     * */
    public <T> void addToRequestQueue(String tag,Request<T> req) {
        if (tag!=null)
            req.setTag(tag);
        mRequestQueue.add(req);
    }

    /*
    * Para hacer posts en Drupal, se necesita completar dos pasos
    * 1º Realizar una llamada a una url que devuelve un token de autenticación (tokenURL)
    * 2º Una vez se recibe el token de autenticación, se realiza la petición POST al servidor con el token.
    */
    private StringRequest getToken(final String data){

        StringRequest mStringRequest = new StringRequest(Request.Method.GET, mUrl+tokenURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String token = response;//recogida token.
                Log.v("token ", token);

                mVolleySingleton.addToRequestQueue(proposalPost(token,data));
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        return mStringRequest;
    }
    public static StringRequest proposalPost(final String token,final String data){
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Response server", response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public String getBodyContentType() {
                return super.getBodyContentType();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return data != null ? data.getBytes() : null;
                //return super.getBody();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders().put("Content-Type","application/json");
                super.getHeaders().put("X-CSRF-Token",token);
                return super.getHeaders();
            }
        };


        return mStringRequest;
    }
    //Método wrapper de la petición POST a Drupal.
    public void makePost(String data){
        mVolleySingleton.addToRequestQueue(getToken(data));
    }



    //Dar una vuelta //Cranear un poco
    /**
     * jsonArray
     *
     * */
    JSONArray jsonArray;
    //no funciona al ser async
    public void pullJSONarrayFromServer(){
        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta Json
                        setJsonArray(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d(TAG, "Error Volley: " + error.toString());
                        error.printStackTrace();
                    }
                }
        );
        mVolleySingleton.addToRequestQueue(mJsonArrayRequest);
    }
    public JSONArray getJsonArray() {
        return jsonArray;
    }
    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }


}