package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

/**
 * Created by Miguel on 5/28/2016.
 */
public class GetPropuestas extends AsyncTask<Void, Void, JSONArray> {
    JSONArray jsonArray;
    @Override
    protected JSONArray doInBackground(Void... params) {
        Log.v("disparado","desdeGetPropuestas");
        jsonArray=new JSONArray();
        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        Log.v("onPostExecute",String.valueOf(jsonArray.length()));
        super.onPostExecute(jsonArray);
    }
}
