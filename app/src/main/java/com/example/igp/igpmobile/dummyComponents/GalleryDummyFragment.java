package com.example.igp.igpmobile.dummyComponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igp.igpmobile.R;
import com.example.igp.igpmobile.common.BaseFragment;

/**
 * Created by vimal on 15/12/15.
 */
public class GalleryDummyFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_gallery_dummy, null);
    }
}
