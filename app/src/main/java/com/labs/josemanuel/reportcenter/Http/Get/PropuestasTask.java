package com.labs.josemanuel.reportcenter.Http.Get;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Miguel on 5/28/2016.
 */
public class PropuestasTask extends AsyncTask<RequestFuture<JSONArray>, Void, Propuesta[]> {

    @Override
    protected Propuesta[] doInBackground(RequestFuture<JSONArray>... params) {
        try {
            return JSONHandler.generatePropuestaArray(params[0].get(10000, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException |TimeoutException e) {
            //Verifica que el error se ha producido por parte de Volley
            if (e.getCause() instanceof VolleyError){
                //Referenciamos el error como un Volley Error (Casteo)
                VolleyError volleyError = (VolleyError)e.getCause();
                //Mostramos el error por el log
                Log.v("ErrorfromPropuestasTask",String.valueOf(volleyError.networkResponse.statusCode));
                //Devolvemos un comentario con todos sus campos a -1
                return null;
            }else{
                //Si el error no lo ha producido algún mecanismo de VolleyError, dispara la causa por la que se ha producido
                Log.v("OtherError",e.getCause().getMessage());
                ////Devolvemos un comentario con todos sus campos a -1
                return null;
            }
        }
    }

    @Override
    protected void onPostExecute(Propuesta[] jsonArray) {
        super.onPostExecute(jsonArray);
    }
}
