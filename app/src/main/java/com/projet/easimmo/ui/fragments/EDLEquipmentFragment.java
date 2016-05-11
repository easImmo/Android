package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.EDLEquipmentAdapter;
import com.projet.easimmo.common.adapter.PropertyAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class EDLEquipmentFragment extends Fragment {

    private List<AssessmentDTO> mAssessmentDTOs;
    private RecyclerView mRecyclerView;
    private EDLEquipmentAdapter mAdapter;

    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private EDLEquipmentCallback mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edlequipment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.equipment_list);

        mRecyclerView.setHasFixedSize(false);
        ReportDTO reportDTO = (ReportDTO) getArguments().getSerializable("report");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAssessmentDTOs = new ArrayList<AssessmentDTO>();
        mAssessmentDTOs.addAll(reportDTO.getAssessmentDTOList());
        mAdapter = new EDLEquipmentAdapter(mAssessmentDTOs);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mAssessmentDTOs.get(position));
            }
        });

        return rootView;
    }

    public interface EDLEquipmentCallback{
        void onItemSelected(AssessmentDTO assessmentDTO);
    }

    public static EDLEquipmentFragment newInstance(ReportDTO reportDTO) {
        EDLEquipmentFragment f = new EDLEquipmentFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("report", reportDTO);
        f.setArguments(bdl);
        return f;
    }

}
