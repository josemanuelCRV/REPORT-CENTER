package com.labs.josemanuel.reportcenter.Http;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Miguel on 6/2/2016.
 */
public class GetCommentWithUser extends AsyncTask<RequestFuture<JSONObject>, Void, CommentWithUser> {
    Context mContext;
    public GetCommentWithUser(Context context){
        mContext=context;
    }

    @Override
    protected CommentWithUser doInBackground(RequestFuture<JSONObject>... params) {
        Log.v("disparado","desde GetComentarios");
        try {
            CommentWithUser commentWithUser = JSONHandler.generateCommentWithUser(params[0].get());
            String mUrl= String.format("http://stag.hackityapp.com/api/user/%s?_format=api_json",commentWithUser.getId_data_uid_relationships());
            User mUser;
            ClienteHttp mClienteHttp = new ClienteHttp(mUrl,mContext);
            try {
                mUser = mClienteHttp.getUsuario().get();
                commentWithUser.setName_user(mUser.getName());
                commentWithUser.setPic_user(mUser.getUser_picture());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                commentWithUser.setPic_user("http://a1.files.biography.com/image/upload/c_fill,cs_srgb,dpr_1.0,g_face,h_300,q_80,w_300/MTE5NDg0MDU1MjQ5OTc4ODk1.jpg");
                commentWithUser.setName_user("chuck norris");
            }
            commentWithUser.setPic_user("http://a1.files.biography.com/image/upload/c_fill,cs_srgb,dpr_1.0,g_face,h_300,q_80,w_300/MTE5NDg0MDU1MjQ5OTc4ODk1.jpg");

            Log.v("desdeGetCommentWith", commentWithUser.getName_user());
            Log.v("desdeGetCommentWith", commentWithUser.getPic_user());

            return commentWithUser;
        } catch (InterruptedException | ExecutionException | JSONException e) {
            //Verifica que el error se ha producido por parte de Volley
            if (e.getCause() instanceof VolleyError){
                //Referenciamos el error como un Volley Error (Casteo)
                VolleyError volleyError = (VolleyError)e.getCause();
                //Mostramos el error por el log
                Log.v("ErrorfromGetComentarios",String.valueOf(volleyError.networkResponse.statusCode));
                //Devolvemos un comentario con todos sus campos a -1
                return CommentWithUser.commentWithUserFactory(null,null,null,null,null,null,null,null);
            }else{
                //Si el error no lo ha producido algún mecanismo de VolleyError, dispara la causa por la que se ha producido
                Log.v("OtherError",e.getCause().getMessage());
                ////Devolvemos un comentario con todos sus campos a -1
                return CommentWithUser.commentWithUserFactory(null,null,null,null,null,null,null,null);
            }
        }
    }

    @Override
    protected void onPostExecute(CommentWithUser commentWithUser) {
        super.onPostExecute(commentWithUser);



    }
}
