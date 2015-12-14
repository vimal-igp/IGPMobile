package com.example.igp.igpmobile.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.igp.igpmobile.BaseActivity;
import com.example.igp.igpmobile.MainActivity;
import com.example.igp.igpmobile.SecActivity;
import com.example.igp.igpmobile.R;
import com.example.igp.igpmobile.utilities.database.SqliteHelper;
import com.example.igp.igpmobile.utilities.network.NetworkManager;

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








    // Utility Static methods

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
}
