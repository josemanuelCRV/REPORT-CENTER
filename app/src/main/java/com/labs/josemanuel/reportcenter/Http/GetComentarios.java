package com.labs.josemanuel.reportcenter.Http;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.RequestFuture;
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
public class GetComentarios extends AsyncTask<RequestFuture<JSONObject>, Void, Comment> {


    @Override
    protected Comment doInBackground(RequestFuture<JSONObject>... params) {
        Log.v("disparado","desde GetComentarios");
        try {
            return JSONHandler.generateComment(params[0].get());
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
    protected void onPostExecute(Comment comment) {
        //Log.v("CID",comment.getCid_attributes());
        super.onPostExecute(comment);
    }
}