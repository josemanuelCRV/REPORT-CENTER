package com.labs.josemanuel.reportcenter.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;

    public ClienteHttp(String url,Context context){
        mUrl= url;
        mContext=context;
        //Data
        //Recogemos una instancia de Volley
        volleySingleton = VolleySingleton.getInstance(context);
        //Recogemos una cola de peticiones Http
        requestQueue = volleySingleton.getRequestQueue();
    }


    private StringRequest getToken(final JSONObject data){

        StringRequest mStringRequest = new StringRequest(Request.Method.GET, mUrl+tokenURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String token = response;//recogida token.
                Log.v("token ", token);

                volleySingleton.addToRequestQueue(proposalPost(token,data.toString()));
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

    public void makePost(JSONObject data){
        volleySingleton.addToRequestQueue(getToken(data));
    }




}
