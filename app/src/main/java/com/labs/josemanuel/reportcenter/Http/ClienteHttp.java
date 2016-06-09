package com.labs.josemanuel.reportcenter.Http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Http.Get.CommentWithUserTask;
import com.labs.josemanuel.reportcenter.Http.Get.PropuestasTask;
import com.labs.josemanuel.reportcenter.Http.Get.UsuarioTask;
import com.labs.josemanuel.reportcenter.Http.Post.PostProposal;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Miguel on 5/22/2016.
 */
public class ClienteHttp {
    private static String mUrl;
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

    public AsyncTask<RequestFuture<JSONArray>, Void, Propuesta[]> getPropuestas(){
        String tag_JsonArray_req = "mJsonArrayRequest";
        RequestFuture<JSONArray> future= RequestFuture.newFuture();
        mUrl="https://stag.hackityapp.com/es/api/v1/proposals";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, mUrl,future,future);
        addToRequestQueue(tag_JsonArray_req,jsonArrayRequest);
        return new PropuestasTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future);
    }
    public AsyncTask<RequestFuture<JSONObject>, Void, User> getUsuario(){
        String TAG= "JsonObjectRequest";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequestAuthorized jsonObjectRequest = new JsonObjectRequestAuthorized(Request.Method.GET, mUrl,future,future);

        addToRequestQueue(TAG,jsonObjectRequest);
        return new UsuarioTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future);
    }
    public AsyncTask<RequestFuture<JSONObject>, Void, CommentWithUser> getCommentWithUser(){
        String TAG= "commentWithUserRequest";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        JsonObjectRequestAuthorized jsonObjectRequest= new JsonObjectRequestAuthorized(Request.Method.GET, mUrl,future,future);
        addToRequestQueue(TAG,jsonObjectRequest);

        return new CommentWithUserTask(mContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future); //doInBackground, onPostExecute
    }
    public AsyncTask<RequestFuture<JSONObject>, Void, String> postProposal(JSONObject postBody){
        String TAG= "postProposal";
        RequestFuture<JSONObject> future= RequestFuture.newFuture();
        mUrl= "http://stag.hackityapp.com/entity/node?_format=hal_json";
        JsonPostObjectRequestAuthorized jsonObjectRequest= new JsonPostObjectRequestAuthorized(Request.Method.POST, mUrl,postBody,future,future);
        //Añadir JSONOBJECT a la petición
        addToRequestQueue(TAG,jsonObjectRequest);
        return new PostProposal(mContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,future);
    }
    public JSONObject generateProposal(){
        Infrastructure.setDummyProposal();
        return Infrastructure.getDummyProposal();
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
