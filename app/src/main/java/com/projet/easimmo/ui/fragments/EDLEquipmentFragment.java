package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.EDLEquipmentAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.display.EdlDetailDisplayDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceReports;
import com.projet.easimmo.ui.dialogFragments.CreateAssessmentDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EDLEquipmentFragment extends Fragment {

    private List<EdlDetailDisplayDTO> mEdlDetailDisplayDTOs;

    @Bind(R.id.equipment_list)
    RecyclerView mRecyclerView;

    private EDLEquipmentAdapter mAdapter;
    private PropertyDTO propertyDTO;

    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.addAssessmentFab)
    FloatingActionButton addAssessmentFab;
    private EDLEquipmentCallback mCallback;
    private ReportDTO reportDTO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edlequipment, container, false);

        ButterKnife.bind(this,rootView);
        mRecyclerView.setHasFixedSize(false);
        reportDTO = (ReportDTO) getArguments().getSerializable("report");
        propertyDTO = (PropertyDTO) getArguments().getSerializable("property");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mEdlDetailDisplayDTOs = new ArrayList<>();

        for(final AssessmentDTO assessmentDTO : reportDTO.getAssessmentDTOList()) {
            addInListEdlDetail(assessmentDTO);
        }

        mAdapter = new EDLEquipmentAdapter(mEdlDetailDisplayDTOs);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mEdlDetailDisplayDTOs.get(position));
            }
        });
        if (addAssessmentFab != null) {
            addAssessmentFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    CreateAssessmentDialog dialog = new CreateAssessmentDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("report_id", reportDTO.getmId());
                    dialog.setArguments(bundle);
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            onResume();
                        }
                    });
                    dialog.show(fm, "dialog");
                }
            });
        }

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ServiceReports serviceReports = new ServiceReports();
                serviceReports.getReport(reportDTO.getmId(), new ICallback<ReportDTO>() {

                    @Override public void success(ReportDTO reportDTO2) {
                        reportDTO = reportDTO2;
                        mEdlDetailDisplayDTOs.clear();
                        for(final AssessmentDTO assessmentDTO : reportDTO.getAssessmentDTOList()) {
                            addInListEdlDetail(assessmentDTO);
                        }
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);

                    }

                    @Override public void failure(Throwable error) {

                    }

                    @Override
                    public void unauthorized() {

                    }


                });
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        ServiceReports serviceReports = new ServiceReports();
        serviceReports.getReport(reportDTO.getmId(), new ICallback<ReportDTO>() {

            @Override public void success(ReportDTO reportDTO2) {
                reportDTO = reportDTO2;
                mEdlDetailDisplayDTOs.clear();
                for(final AssessmentDTO assessmentDTO : reportDTO.getAssessmentDTOList()) {
                    addInListEdlDetail(assessmentDTO);
                }
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);

            }

            @Override public void failure(Throwable error) {

            }

            @Override
            public void unauthorized() {

            }


        });
        super.onResume();
    }


    private void addInListEdlDetail(AssessmentDTO assessmentDTO){
        RoomDTO roomTmp = null;
        EquipmentDTO equipmentTmp = null;
        for(RoomDTO room : propertyDTO.getRoomDTOList()){
            for(EquipmentDTO equipment : room.getEquipmentDTOList()){
                if(equipment.getmId().equals(assessmentDTO.getEquipmentDTO())){
                    roomTmp = room;
                    equipmentTmp = equipment;
                    break;
                }
            }
        }
        EdlDetailDisplayDTO detail = new EdlDetailDisplayDTO(propertyDTO,assessmentDTO,equipmentTmp,roomTmp);
        if(mEdlDetailDisplayDTOs == null){
            mEdlDetailDisplayDTOs = new ArrayList<>();
        }
        mEdlDetailDisplayDTOs.add(detail);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EDLEquipmentCallback) {
            mCallback = (EDLEquipmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement EDLEquipmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    public interface EDLEquipmentCallback{
        void onItemSelected(EdlDetailDisplayDTO edlDetailDisplayDTO);
    }

    public static EDLEquipmentFragment newInstance(ReportDTO reportDTO, PropertyDTO propertyDTO) {
        EDLEquipmentFragment f = new EDLEquipmentFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("report", reportDTO);
        bdl.putSerializable("property", propertyDTO);
        f.setArguments(bdl);
        return f;
    }

}
