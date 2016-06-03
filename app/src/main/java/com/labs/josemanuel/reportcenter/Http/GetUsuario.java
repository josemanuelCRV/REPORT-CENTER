package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Miguel on 31/05/2016.
 */
public class GetUsuario extends AsyncTask<RequestFuture<JSONObject>, Void, User> {

    @Override
    protected User doInBackground(RequestFuture<JSONObject>... params) {
        Log.v("disparado","desdeGetUsuario");
        try {
            User user = JSONHandler.generateUser(params[0].get());
            Log.v("User",user.getName());
            return user;
        } catch (InterruptedException | ExecutionException |  JSONException e) {
            //Verifica que el error se ha producido por parte de Volley
            if (e.getCause() instanceof VolleyError){
                //Referenciamos el error como un Volley Error (Casteo)
                VolleyError volleyError = (VolleyError)e.getCause();
                //Mostramos el error por el log
                Log.v("ErrorfromGetComentarios",String.valueOf(volleyError.networkResponse.statusCode));
                //Devolvemos un usuario con todos sus campos a -1
                return User.userFactory(null,null,null,null,null,null,null,null,null,null,null,null,null,null);
            }else{
                //Si el error no lo ha producido algún mecanismo de VolleyError, dispara la causa por la que se ha producido
                Log.v("OtherError",e.getCause().getMessage());
                ////Devolvemos un usuario con todos sus campos a -1
                return User.userFactory(null,null,null,null,null,null,null,null,null,null,null,null,null,null);
            }
        }
    }

    @Override
    protected void onPostExecute(User user) {
       // Log.v("User",user.getName());
        super.onPostExecute(user);
    }
}
