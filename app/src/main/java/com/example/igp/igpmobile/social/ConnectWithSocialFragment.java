package com.example.igp.igpmobile.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igp.igpmobile.R;

/**
 * Created by vimal on 10/12/15.
 */
public class ConnectWithSocialFragment extends SocialLoginFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_socal_login, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btnConnectWithFacebook){
            facebookSignInInitiate();
        } else if(v.getId()== R.id.btnConnectWithGoogle){
            googleSignInInitiate();
        }else if(v.getId()== R.id.btnConnectWithEmail){
            emailSignInInitiate();
        }
    }

    private void setUpView(View view){
        view.findViewById(R.id.btnConnectWithEmail).setOnClickListener(this);
        view.findViewById(R.id.btnConnectWithGoogle).setOnClickListener(this);
        view.findViewById(R.id.btnConnectWithFacebook).setOnClickListener(this);
    }
}
