package com.projet.easimmo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;

public class GeneralPropertyFragment extends Fragment {

    private PropertyDTO property;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_property, container, false);
        property = (PropertyDTO) getArguments().getSerializable("property");
        return v;
    }

    public static GeneralPropertyFragment newInstance(PropertyDTO property) {
        GeneralPropertyFragment f = new GeneralPropertyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("property", property);
        f.setArguments(bdl);
        return f;
    }

}
