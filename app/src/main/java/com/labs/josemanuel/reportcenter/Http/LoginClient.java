package com.labs.josemanuel.reportcenter.Http;

/**
 * Created by Miguel on 5/26/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;


import android.support.v7.app.AlertDialog;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.labs.josemanuel.reportcenter.ActividadListaPropuestas;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;
import com.labs.josemanuel.reportcenter.R;


/**
 * Created by Miguel on 5/9/2016.
 */
public class LoginClient {
    public String API_URL;
    Context mContext;

    //New Era
    RequestQueue mRequestQueue;
    StringRequest stringRequest;

    public LoginClient(Context context){
        mContext=context;
        API_URL = mContext.getApplicationContext().getResources().getString(R.string.URL_LOGIN);
        mRequestQueue= Volley.newRequestQueue(mContext);
    }

    public void loginWithServer(String first_name, String password) {
        //Body params
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", first_name);
            jsonBody.put("password", password);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        final String mRequestBody = jsonBody.toString();

        stringRequest = new StringRequest(
                Request.Method.POST,
                API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Token del Server", response);
                        Intent i = new Intent (mContext, ActividadListaPropuestas.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error.printStackTrace();
            }
        })
        {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    byte[]  bodyData= response.data;
                    //bodyData no hay token, no hay login
                    if (bodyData != null) {
                        try {
                            responseString= new String(bodyData, "UTF-8");
                            Log.v("Body!!",responseString);
                            OutputStreamWriter osw=null;
                            try {
                                osw= new OutputStreamWriter(mContext.openFileOutput("clave.txt",mContext.MODE_PRIVATE));
                                osw.write(ClienteHttp.getStringFromJSONObjectBackend(responseString,"token"));
                                Credentials.setUserId(ClienteHttp.getStringFromJSONObjectBackend(responseString,"userId"));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally{
                                try {
                                    osw.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        // can get more details such as response.headers
                    }else{
                        Log.v("Fallo login", "no hay token");
                    }
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        stringRequest.setTag("POST"); //Permite que continue la petición POST a pesar de cambiar de actividad
        mRequestQueue.add(stringRequest);
    }
    private AlertDialog.Builder getBuilder(VolleyError error) {
        return new AlertDialog.Builder(mContext)
                .setTitle(String.valueOf(error.networkResponse.statusCode))
                .setMessage("El usuario o la contraseña es incorrecta")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
    }
}




