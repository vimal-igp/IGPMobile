package com.example.igp.igpmobile.utilities.network;

import android.content.Context;

import com.example.igp.igpmobile.utilities.network.APIdata.LoginRequestApiData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vimal on 9/12/15.
 */
public class PostParameters {

    public static Map<String, String> getHeadersForRequest(Context context) {
        HashMap<String, String> map = new HashMap<>();
        map.put("param1", "PARAM1");
        map.put("param2", "PARAM2");

        // OTHER PARAMTERS TO BE POSTED
        return map;
    }

    // LOGIN JSON
    public static JSONObject loginRequestJSON(LoginRequestApiData data){
        JSONObject paramJSON;
        HashMap<String, String> map = new HashMap<>();
        map.put("userHashCode", data.getUserHashCode());
        map.put("userEmail",data.getUserEmail());
        paramJSON = new JSONObject(map);
        return paramJSON;
    }
}
