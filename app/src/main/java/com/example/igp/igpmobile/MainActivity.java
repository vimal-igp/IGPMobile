package com.example.igp.igpmobile;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

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

public class MainActivity extends BaseActivity {

    private static final String TAG = "IGP:MainAct:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SQLTestCode();
        //JsonRequestTest();
        openRelevantFragment();
    }

    private void openRelevantFragment(){
        if(isValidString(MyPrefs.getUserHashCode(this))){
            openUserHomePageFragment();
        }
        else{
            openSocialLoginFragment();
        }
    }

    private void openUserHomePageFragment(){
        BaseFragment fragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction().add(MAIN_ACTIVITY_CONTAINER_ID,fragment).commitAllowingStateLoss();
    }

    private void openSocialLoginFragment(){
        BaseFragment fragment = new ConnectWithSocialFragment();
        getSupportFragmentManager().beginTransaction().add(MAIN_ACTIVITY_CONTAINER_ID,fragment).commitAllowingStateLoss();
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
