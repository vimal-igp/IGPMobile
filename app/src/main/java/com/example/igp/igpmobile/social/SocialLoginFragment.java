package com.example.igp.igpmobile.social;

import android.accounts.AccountManager;
import android.content.Context;

import com.example.igp.igpmobile.common.BaseFragment;

/**
 * Created by vimal on 10/12/15.
 */
public class SocialLoginFragment extends BaseFragment {

    protected void facebookSignInInitiate(){

    }

    protected void googleSignInInitiate(){
        AccountManager am = (AccountManager) getActivity().getSystemService(Context.ACCOUNT_SERVICE);

    }

    protected void emailSignInInitiate(){
        
    }
}
