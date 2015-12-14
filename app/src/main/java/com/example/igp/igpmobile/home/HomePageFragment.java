package com.example.igp.igpmobile.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igp.igpmobile.common.BaseFragment;
import com.example.igp.igpmobile.R;

/**
 * Created by vimal on 10/12/15.
 */
public class HomePageFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_home_page,null);
    }
}
