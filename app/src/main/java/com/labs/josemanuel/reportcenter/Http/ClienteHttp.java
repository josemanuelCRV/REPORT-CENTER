package com.labs.josemanuel.reportcenter.Http;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Miguel on 5/22/2016.
 */
public class ClienteHttp {
    private static String mUrl;

    String tokenURL= "/user/rest/session/token";
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

                //----------------REFACTOR!
                if (token != null) {
                    OutputStreamWriter osw=null;
                    try {
                        if(PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean("login",true)){
                            osw= new OutputStreamWriter(mContext.openFileOutput("clave.txt",mContext.MODE_PRIVATE));
                            osw.write(ClienteHttp.getStringFromJSONObjectBackend(token,"tokenCSRF"));
                            Credentials.setUserId(ClienteHttp.getStringFromJSONObjectBackend(token,"userId"));
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit().putBoolean("login",false);
                            editor.apply();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            osw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // can get more details such as response.headers
                    }


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
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, mUrl+"/user/", new Response.Listener<String>() {
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
                Map<String,String> params = new HashMap<>();
                try {
                    JSONObject aux = new JSONObject(token);
                    params.put("X-CSRF-Token",aux.getString("tokenCSRF"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };


        return mStringRequest;
    }
    //Método wrapper de la petición POST a Drupal.
    public void makePost(String data){
        mVolleySingleton.addToRequestQueue(getToken(data));
    }

    public void doLogin(String data){
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

    public JSONObject getCommentFromCid(String cid){
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mUrl + "/es/comment/" + cid + "?_format=json",future,future);

        mVolleySingleton.addToRequestQueue(jsonObjectRequest);

        try {
            return future.get(2500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getStringFromJSONObjectBackend(String jsonString,String fieldName){
        String output;
        JSONObject jsonObject;
        try {
            jsonObject= new JSONObject(jsonString);
            output=jsonObject.getString(fieldName);
        } catch (JSONException e) {
            e.printStackTrace();
            output="Error: nothing";
        }
        return output;
    }


    public AsyncTask<Void, Void, JSONArray> getPropuestas(){
        RequestFuture<JSONArray> future= RequestFuture.newFuture();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrl,future,future);
        mVolleySingleton.getRequestQueue().add(jsonArrayRequest);

        /*try {
            return future.get(10500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;*/

            Log.v("done in Background","Straight outta madridClienteHttp");

            return new GetPropuestas().execute();

    }
    /*http://programminglife.io/android-volley-synchronous-request/
    public void fetchData(final DataCallback callback) {
        String url = "your-url-here";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(tag, response.toString());

                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            Log.e(tag, e.getMessage(), e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(tag, "Error: " + error.getMessage());
                    }
                });

        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
    public void useData() {
        fetchData(new DataCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    data = result.getString("data");
                } catch (JSONException e) {
                    Log.e(tag, e.getMessage(), e);
                }
            }
        });
    }
*/

}
