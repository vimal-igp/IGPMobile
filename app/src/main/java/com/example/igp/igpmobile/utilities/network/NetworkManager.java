package com.example.igp.igpmobile.utilities.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.igp.igpmobile.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vimal on 9/12/15.
 */
public class NetworkManager implements RequestQueue.RequestFilter {

    // SINGLETON NETWORK MANAGER

    private static final String TAG = "IGP:NetMngr:";
    private static final int SOCKET_TIMEOUT_MS = 10000; //10 seconds


    private static RequestQueue requestQueue;
    private static String lastUrl;

    private String BASE_URL;
    private NetworkManager(Context context) {
        if (requestQueue == null) {
            BASE_URL= context.getString(R.string.app_base_url);
            lastUrl = "";
            requestQueue = Volley.newRequestQueue(context, null, null);
            requestQueue.start();
        }
    }

    public static NetworkManager newInstance(Context context) {
        NetworkManager instance = new NetworkManager(context);
        return instance;
    }

    private void addRequest(Request<?> request) {
        requestQueue.add(request);
    }


    // JSON REQUEST WRAPPER
    public Request<?> jsonRequest(final Context context, int identifier, String url,
                                  JSONObject object, Response.Listener<JSONObject> listener,
                                  Response.ErrorListener errorListener, boolean shouldCache) {

        // THE ACTUAL JSON OBJECT REQUEST
        JsonObjectRequest request = new JsonObjectRequest(BASE_URL + url, object, listener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.putAll(PostParameters.getHeadersForRequest(context));
                return params;
            }
        };

        request.setIdentifier(identifier);
        request.setShouldCache(shouldCache);
        request.setTag(this);
        request.setRetryPolicy(new DefaultRetryPolicy(SOCKET_TIMEOUT_MS, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        try {
            Log.i(TAG, "-------------------------");
            Log.i(TAG, "REQUEST HEADERS: " + request.getHeaders());
            Log.i(TAG, "REQUEST URL: " + request.getUrl());
            Log.i(TAG, "REQUEST PARAMS: " + request.getBodyString());
            Log.i(TAG, "REQUEST ID: " + request.getIdentifier());
            Log.i(TAG, "-------------------------");
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }

        addRequest(request);
        return request;
    }


    public void cancel() {
        Log.i(TAG, "-------------------------");
        Log.i(TAG, "LAST PINGED URL: " + lastUrl);
        Log.i(TAG, "REQUEST QUEUE CACHE: " + requestQueue.getCache().get(lastUrl));

        requestQueue.cancelAll(this);
        requestQueue.getCache().clear();

        Log.i(TAG, "REQUEST QUEUE CACHE AFTER CLEAR: " + requestQueue.getCache().get(lastUrl));
        Log.i(TAG, "-------------------------");

    }

    @Override
    public boolean apply(Request<?> request) {

        return request.getTag() == this;
    }

}
