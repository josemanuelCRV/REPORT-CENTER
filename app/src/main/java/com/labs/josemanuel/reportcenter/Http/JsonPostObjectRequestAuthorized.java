package com.labs.josemanuel.reportcenter.Http;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miguel on 6/5/2016.
 */

public class JsonPostObjectRequestAuthorized extends JsonObjectRequest {
    JSONObject mProposalData;

    public JsonPostObjectRequestAuthorized(int method, String url,JSONObject proposalData,Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        mProposalData=proposalData;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> params= new HashMap<>();
        params.put("Authorization", Credentials.getAuthorization());
        params.put("X-CSRF-Token",Credentials.getX_CSRF_Token());
        params.put("Content-Type", "application/hal+json");
        return params;
    }

    @Override
    public byte[] getBody() {
        try {
            return mProposalData.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}