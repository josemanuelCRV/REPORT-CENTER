package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Miguel on 5/31/2016.
 */
public class GetComment extends AsyncTask<RequestFuture<JSONObject>, Void, Comment> {


    @Override
    protected Comment doInBackground(RequestFuture<JSONObject>... params) {
        Log.v("disparado","desde GetComentarios");
        try {
            return JSONHandler.generateComment(params[0].get());
        } catch (InterruptedException | ExecutionException |  JSONException e) {
            //Verifica que el error se ha producido por parte de Volley
            if (e.getCause() instanceof VolleyError){
                //Referenciamos el error como un Volley Error (Casteo)
                VolleyError volleyError = (VolleyError)e.getCause();
                //Mostramos el error por el log
                Log.v("ErrorfromGetComentarios",String.valueOf(volleyError.networkResponse.statusCode));
                //Devolvemos un comentario con todos sus campos a -1
                return Comment.commentFactory(null,null,null,null,null,null);
            }else{
                //Si el error no lo ha producido algún mecanismo de VolleyError, dispara la causa por la que se ha producido
                Log.v("OtherError",e.getCause().getMessage());
                ////Devolvemos un comentario con todos sus campos a -1
                return Comment.commentFactory(null,null,null,null,null,null);
            }
        }
    }

    @Override
    protected void onPostExecute(Comment comment) {
        super.onPostExecute(comment);

    }
}