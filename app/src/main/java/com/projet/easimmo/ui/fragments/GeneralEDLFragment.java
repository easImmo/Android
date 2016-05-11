package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.ReportDTO;


public class GeneralEDLFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general_edl, container, false);
        return rootView;
    }

    public static GeneralEDLFragment newInstance(ReportDTO reportDTO) {
        GeneralEDLFragment f = new GeneralEDLFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("report", reportDTO);
        f.setArguments(bdl);
        return f;
    }
}
