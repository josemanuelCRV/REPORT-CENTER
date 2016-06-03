package com.labs.josemanuel.reportcenter.Http;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.HttpAuthHandler;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    //Aplicamos el patrón Singleton en el uso de Volley para generar una única instancia de una RequestQueue, o cola de peticiones
    VolleySingleton mVolleySingleton;
    RequestQueue mRequestQueue;

    public static void setmUrl(String mUrl) {
        ClienteHttp.mUrl = mUrl;
    }

    public ClienteHttp(String url, Context context){
        mUrl = url;
        mContext = context;
        initiate();

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
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit().putBoolean("login",false);
                editor.apply();
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
    public AsyncTask<RequestFuture<JSONArray>, Void, Propuesta[]> getPropuestas(){
        String tag_JsonArray_req = "mJsonArrayRequest";
        RequestFuture<JSONArray> future= RequestFuture.newFuture();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrl,future,future);
        addToRequestQueue(tag_JsonArray_req,jsonArrayRequest);
        return new GetPropuestas().execute(future);
    }
    public AsyncTask<RequestFuture<JSONObject>, Void, User> getUsuario(){
        String TAG= "JsonObjectRequest";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequestAuthorized jsonObjectRequest = new JsonObjectRequestAuthorized(Request.Method.GET, mUrl,future,future);

        addToRequestQueue(TAG,jsonObjectRequest);
        return new GetUsuario().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future);
    }

    public AsyncTask<RequestFuture<JSONObject>, Void, CommentWithUser> getCommentWithUser(){
        String TAG= "commentWithUserRequest";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequestAuthorized jsonObjectRequest= new JsonObjectRequestAuthorized(Request.Method.GET, mUrl,future,future);
        addToRequestQueue(TAG,jsonObjectRequest);

        return new GetCommentWithUser(mContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future); //doInBackground, onPostExecute
    }
    public AsyncTask<RequestFuture<JSONObject>, Void, Comment> getComment(){
        String TAG= "commentRequest";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequestAuthorized jsonObjectRequest= new JsonObjectRequestAuthorized(Request.Method.GET, mUrl,future,future);
        addToRequestQueue(TAG,jsonObjectRequest);

        return new GetComment().execute(future); //doInBackground, onPostExecute
    }


    /**
     * El método permite comprobar si hay conexión a red.
     *
     * @return isAvailable true si hay red
     */
    public boolean isNetworkAvailable() {
        //Gestor de conectividad
        ConnectivityManager manager;
        manager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Objeto que recupera el estado
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
