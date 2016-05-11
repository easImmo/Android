package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GeneralEDLFragment extends Fragment {


    @Bind(R.id.edl_date_created)
    TextView _edlDate;

    @Bind(R.id.nb_problems)
    TextView _nbProblems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general_edl, container, false);
        ButterKnife.bind(this, rootView);
        ReportDTO reportDTO = (ReportDTO) getArguments().getSerializable("report");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        _edlDate.setText(sdf.format(reportDTO.getmCreated_at()));
        if(reportDTO.getAssessmentDTOList() != null) {
            _nbProblems.setText(reportDTO.getAssessmentDTOList().size()+"");
        }else{
            _nbProblems.setText("0");
        }
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
