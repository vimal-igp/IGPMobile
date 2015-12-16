package com.example.igp.igpmobile;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.igp.igpmobile.dummyComponents.CameraDummyFragment;
import com.example.igp.igpmobile.dummyComponents.GalleryDummyFragment;
import com.example.igp.igpmobile.dummyComponents.ManageDummyFragment;
import com.example.igp.igpmobile.dummyComponents.SlideDummyFragment;
import com.example.igp.igpmobile.utilities.constants.ConstantKeys;
import com.example.igp.igpmobile.utilities.data.UserData;
import com.example.igp.igpmobile.common.BaseFragment;
import com.example.igp.igpmobile.social.ConnectWithSocialFragment;
import com.example.igp.igpmobile.home.HomePageFragment;
import com.example.igp.igpmobile.utilities.network.APIdata.LoginRequestApiData;
import com.example.igp.igpmobile.utilities.network.PostParameters;
import com.example.igp.igpmobile.utilities.preferences.MyPrefs;

import org.json.JSONObject;

import java.util.List;

import static com.example.igp.igpmobile.utilities.Utils.isValidString;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "IGP:MainAct:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpOtherViews();
        //SQLTestCode();
        //JsonRequestTest();
        openRelevantFragment();
    }

    private void setUpOtherViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void openRelevantFragment(){
        /*
        if(isValidString(MyPrefs.getUserHashCode(this))){
            openUserHomePageFragment();
        }
        else{
            openSocialLoginFragment();
        }
        */
        openUserHomePageFragment();
    }

    private void openUserHomePageFragment(){
        BaseFragment fragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction().add(MAIN_ACTIVITY_CONTAINER_ID,fragment).commitAllowingStateLoss();
    }

    private void openSocialLoginFragment(){
        BaseFragment fragment = new ConnectWithSocialFragment();
        getSupportFragmentManager().beginTransaction().add(MAIN_ACTIVITY_CONTAINER_ID,fragment).commitAllowingStateLoss();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            openCameraFragment();
        } else if (id == R.id.nav_gallery) {
            openGalleryFragment();
        } else if (id == R.id.nav_slideshow) {
            openSlideFragment();
        } else if (id == R.id.nav_manage) {
            openManageFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openCameraFragment(){
        BaseFragment.addToBackStackFromNavigationDrawer(this, new CameraDummyFragment());
    }

    private void openGalleryFragment(){
        BaseFragment.addToBackStackFromNavigationDrawer(this, new GalleryDummyFragment());
    }

    private void openSlideFragment(){
        BaseFragment.addToBackStackFromNavigationDrawer(this, new SlideDummyFragment());
    }

    private void openManageFragment(){
        BaseFragment.addToBackStackFromNavigationDrawer(this, new ManageDummyFragment());
    }


    private void SQLTestCode(){

        UserData userData = new UserData();
        userData.setId(1);
        userData.setCode("code1");
        userData.setTitle("title1");

        UserData userData2 = new UserData();
        userData2.setId(2);
        userData2.setCode("code2");
        userData2.setTitle("title2");

        try {
            sqliteHelper.addUserData(userData);
            sqliteHelper.addUserData(userData2);

            Log.d(TAG, "userData data saved:");

            List<UserData> userDatas = sqliteHelper.getUserData();
            Log.d(TAG, "retrieved data: " + userDatas.get(0).getCode()+" and "+ userDatas.get(1).getCode());


        }catch (SQLiteException e){
            Log.d(TAG, ""+ e.toString());
        }
    }

    private void JsonRequestTest(){
        Log.d(TAG, "REQUEST FOR USER DATA");
        String tailUrl="";

        // prepare data and convert to json
        LoginRequestApiData loginData= new LoginRequestApiData();
        loginData.setUserHashCode("US33240CCZ8");
        loginData.setUserEmail("xyz@igp.com");
        JSONObject paramJSON =PostParameters.loginRequestJSON(loginData);

        networkManager.jsonRequest(this, ConstantKeys.TEST_KEY, tailUrl, paramJSON, this, this, false);
    }



}
