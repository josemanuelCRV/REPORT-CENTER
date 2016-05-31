package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
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
            return JSONHandler.generateUser(params[0].get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        Log.v("User",user.getName());
        super.onPostExecute(user);
    }
}
