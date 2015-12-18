package com.example.igp.igpmobile;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by vimal on 15/12/15.
 */
public class IGPMobile extends Application {

    private static final String TAG = "IGP:App";
    private static final String TRACKER_ID = "UA-54553092-2";
    private Tracker mTracker;

    public synchronized Tracker getAnalyticsTracker(){
        if(mTracker== null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(TRACKER_ID);
        }
        return mTracker;
    }
}
