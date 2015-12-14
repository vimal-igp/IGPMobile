package com.example.igp.igpmobile.utilities.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.igp.igpmobile.utilities.Utils;

/**
 * Created by vimal on 9/12/15.
 */
public class MyPrefs {

    private static final String prefsFile = "general_settings";
    public static final String KEY_USER_HASH_CODE = "userHashCode";

    public static void saveUserHashCode(Context context, String hashCode){
        SharedPreferences prefs = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_USER_HASH_CODE, hashCode).commit();
    }

    public static String getUserHashCode(Context context){
        SharedPreferences prefs = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_HASH_CODE, Utils.EMPTY_STRING);
    }
}
