package com.example.igp.igpmobile.utilities;

/**
 * Created by vimal on 9/12/15.
 */
public class Utils {
    public static final String EMPTY_STRING ="";

    public static boolean isValidString(String testString) {
        if(testString != null && !("").equals(testString) && !("null").equals(testString)){
            return true;
        }
        else {
            return false;
        }
    }
}
