package com.example.igp.igpmobile.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.igp.igpmobile.BaseActivity;
import com.example.igp.igpmobile.IGPMobile;
import com.example.igp.igpmobile.MainActivity;
import com.example.igp.igpmobile.SecActivity;
import com.example.igp.igpmobile.R;
import com.example.igp.igpmobile.utilities.database.SqliteHelper;
import com.example.igp.igpmobile.utilities.network.NetworkManager;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by vimal on 9/12/15.
 */
public class BaseFragment extends Fragment {

    private static final String TAG = "IGP:BaseFrag:";

    private NetworkManager networkManager;
    private SqliteHelper sqliteHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.networkManager = NetworkManager.newInstance(getActivity());
        //this.sqliteHelper = new SqliteHelper(getActivity());
        //handleUncaughtExceptions();
    }

    @Override
    public void onResume() {
        super.onResume();
    }






    public void handleUncaughtExceptions() {
       Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
           @Override
           public void uncaughtException(Thread thread, Throwable e) {
               e.printStackTrace();
               // not all Android versions will print the stack trace automatically
               StringWriter stackTrace = new StringWriter();
               e.printStackTrace(new PrintWriter(stackTrace));
               getActivity().finish();
           }
       });
    }

    public static void addToBackStackFromNavigationDrawer(Context context, BaseFragment fragment){
        BaseFragment currentFragment = getFragmentFromCurrentActivity(context);

        // check if the currently loaded fragment is same as incoming fragment
        if(!fragment.getClass().getSimpleName().equals(currentFragment.getClass().getSimpleName())){
            addToBackStackInMainActivity(context, fragment);
        }
    }

    public static void replaceStackInMainActivity(Context context, BaseFragment fragment){
        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0){
            fm.popBackStack(fm.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(BaseActivity.MAIN_ACTIVITY_CONTAINER_ID,fragment)
                .commit();
    }

    public static void addToBackStackInMainActivity(Context context, BaseFragment fragment){
       ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(BaseActivity.MAIN_ACTIVITY_CONTAINER_ID, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static BaseFragment getFragmentFromCurrentActivity(Context context) {

        BaseFragment currentFragment;
        int containerId = getContainerIdForCurrentActivity(context);
        currentFragment= (BaseFragment) ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(containerId);
        return currentFragment;
    }

    public static int getContainerIdForCurrentActivity(Context context) {

        int containerId = R.id.mainContainer;
        if(context instanceof MainActivity){
            containerId = BaseActivity.MAIN_ACTIVITY_CONTAINER_ID;
        }else if(context instanceof SecActivity){
            containerId = BaseActivity.SEC_ACTIVITY_CONTAINER_ID;
        }
        return containerId;
    }

    // add this method to every onResume() method of fragment.
    public void registerFragmentWithAnalytics(String fragmentName){
        Tracker tracker = ((IGPMobile)getActivity().getApplication()).getAnalyticsTracker();
        tracker.setScreenName(fragmentName);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }
}
