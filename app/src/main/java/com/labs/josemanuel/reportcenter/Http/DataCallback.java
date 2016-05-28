package com.labs.josemanuel.reportcenter.Http;

import org.json.JSONObject;

/**
 * Created by Miguel on 5/28/2016.
 * http://programminglife.io/android-volley-synchronous-request/
 */
public interface DataCallback {
    void onSuccess(JSONObject result);
}

