package com.labs.josemanuel.reportcenter.Http.Post;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Miguel on 6/5/2016.
 */
public class PostProposal extends AsyncTask<RequestFuture<JSONObject>, Void, String> {
    Context mContext;
    public PostProposal(Context context){
        mContext=context;
    }

    @Override
    protected String doInBackground(RequestFuture<JSONObject>... params) {
        Log.v("Flag","PostProposal");

        try {
            return JSONHandler.getStringFromNode(params[0].get(10000,TimeUnit.MILLISECONDS),"nid");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            if( e instanceof ExecutionException){
                //Referenciamos el error como un Volley Error
                VolleyError volleyError = (VolleyError)e.getCause();
                //Mostramos el error por el log
                Log.v("ErrorfromPropuestasTask",String.valueOf(volleyError.networkResponse.statusCode));
                //Se devuelve el código de estado de la petición
                return String.valueOf(volleyError.networkResponse.statusCode);
            }else{
                //Si el error no lo ha producido algún mecanismo de VolleyError, dispara la causa por la que se ha producido
                Log.v("OtherError",e.getCause().getMessage());
                //Devuelve -1 como señal de que es una JSONException
                return String.valueOf(-1);
            }
        }
    }

    @Override
    protected void onPostExecute(String newPostId) {
        Log.v("newPostId", newPostId);
        super.onPostExecute(newPostId);
    }
}
