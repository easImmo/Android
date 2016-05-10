package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;

public class GeneralProperyFragment extends Fragment {

    private String mIdProperty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_propery, container, false);

        return v;
    }

    public GeneralProperyFragment newInstance(String idProperty) {
        GeneralProperyFragment f = new GeneralProperyFragment();
        this.mIdProperty = idProperty;
        return f;
    }

}
