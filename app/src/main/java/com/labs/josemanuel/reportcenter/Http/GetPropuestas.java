package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import java.util.concurrent.ExecutionException;

/**
 * Created by Miguel on 5/28/2016.
 */
public class GetPropuestas extends AsyncTask<RequestFuture<JSONArray>, Void, Propuesta[]> {

    @Override
    protected Propuesta[] doInBackground(RequestFuture<JSONArray>... params) {
        Log.v("disparado","desdeGetPropuestas");

        try {
            return JSONHandler.generatePropuestaArray(params[0].get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Propuesta[] jsonArray) {
        Log.v("onPostExecute",String.valueOf(jsonArray.length));
        super.onPostExecute(jsonArray);
    }
}
