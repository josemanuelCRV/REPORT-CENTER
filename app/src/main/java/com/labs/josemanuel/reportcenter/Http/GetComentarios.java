package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;

import java.util.concurrent.ExecutionException;

/**
 * Created by Miguel on 5/31/2016.
 */
public class GetComentarios extends AsyncTask<String[], Void, Comment[]> {

    @Override
    protected Comment[] doInBackground(String[]... params) {
        Log.v("disparado","desdeGetPropuestas");
        try {
            return JSONHandler.generateCommentsArray(params[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Comment[] comments) {
        super.onPostExecute(comments);
    }
}