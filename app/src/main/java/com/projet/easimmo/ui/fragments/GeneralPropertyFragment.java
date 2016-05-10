package com.projet.easimmo.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;

public class GeneralPropertyFragment extends Fragment {

    private PropertyDTO property;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_property, container, false);
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.anim_toolbar);


        return v;
    }

    public GeneralPropertyFragment newInstance(PropertyDTO property) {
        GeneralPropertyFragment f = new GeneralPropertyFragment();
        this.property = property;
/*        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setTitle(this.property.getmName());*/
        return f;
    }

}
