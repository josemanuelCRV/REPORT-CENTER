package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Miguel on 5/28/2016.
 */
public class GetPropuestas extends AsyncTask<RequestFuture<JSONArray>, Void, JSONArray> {
    JSONArray jsonArray;
    /*@Override
    protected JSONArray doInBackground(Void... params) {
        Log.v("disparado","desdeGetPropuestas");
        jsonArray=new JSONArray();
        return jsonArray;
    }*/

    @Override
    protected JSONArray doInBackground(RequestFuture<JSONArray>... params) {
        Log.v("disparado","desdeGetPropuestas");

        try {
            return params[0].get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        Log.v("onPostExecute",String.valueOf(jsonArray.length()));
        super.onPostExecute(jsonArray);
    }
}
