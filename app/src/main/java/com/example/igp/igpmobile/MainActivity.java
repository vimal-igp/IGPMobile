package com.example.igp.igpmobile;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.igp.igpmobile.utilities.Utils.isValidString;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "IGP:MainAct:";
    private Toolbar toolbar;
    private SearchBox search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpOtherViews();

        // searchBox related
        search =(SearchBox)findViewById(R.id.searchbox);
        search.enableVoiceRecognition(this);


        //SQLTestCode();
        //JsonRequestTest();
        openRelevantFragment();
    }

    private void setUpOtherViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== R.id.notif_menu){
            // notif logic
        }
        if(id== R.id.search_menu){
            Toast.makeText(this,"SearchBox selected", Toast.LENGTH_SHORT).show();
            openSearch();
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    private void openSearch(){
        toolbar.setTitle("");
        search.revealFromMenuItem(R.id.search_menu, this);
        for (int x = 0; x < 10; x++) {
            SearchResult option = new SearchResult("Result " + Integer.toString(x),getResources().getDrawable(R.drawable.ic_up));
            search.addSearchable(option);
        }
        search.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
                // Hamburger has been clicked
                Toast.makeText(MainActivity.this, "Menu click",
                        Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                // Use this to tint the screen

            }

            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                closeSearch();
            }

            @Override
            public void onSearchTermChanged(String term) {
                // React to the search term changing
                // Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(MainActivity.this, searchTerm + " Searched",
                        Toast.LENGTH_LONG).show();
                toolbar.setTitle(searchTerm);

            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to result being clicked
            }

            @Override
            public void onSearchCleared() {

            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void closeSearch() {
        search.hideCircularly(this);
        if(search.getSearchText().isEmpty())toolbar.setTitle("");
    }
}
