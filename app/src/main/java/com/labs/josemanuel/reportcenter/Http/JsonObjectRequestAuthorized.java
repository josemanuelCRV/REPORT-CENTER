package com.labs.josemanuel.reportcenter.Http;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Usuario on 01/06/2016.
 */
public class JsonObjectRequestAuthorized extends JsonObjectRequest {
    public JsonObjectRequestAuthorized(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> params= new HashMap<>();
        params.put("Authorization", Credentials.getAuthorization());
        params.put("X-CSRF-Token",Credentials.getX_CSRF_Token());
        return params;
    }
}
