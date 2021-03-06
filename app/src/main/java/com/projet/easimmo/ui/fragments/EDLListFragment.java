package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.projet.easimmo.common.adapter.PropertyAdapter;
import com.projet.easimmo.common.adapter.ReportAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceProperties;
import com.projet.easimmo.ui.dialogFragments.CreateEdlDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EDLListFragment extends Fragment {
    private PropertyDTO property;
    private List<ReportDTO> mReportDTOList;

    @Bind(R.id.report_list)
    RecyclerView mRecyclerView;
    private ReportAdapter mAdapter;
    private ServiceProperties serviceProperties;

    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Bind(R.id.addEdlFab)
    FloatingActionButton addRoomFab;

    private ReportListCallback mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edllist, container, false);
        ButterKnife.bind(this,rootView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mReportDTOList = new ArrayList<>();
        property = (PropertyDTO) getArguments().getSerializable("property");

        mReportDTOList.addAll(property.getReportDTOList());
        mAdapter = new ReportAdapter(mReportDTOList);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAdapter);


        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mReportDTOList.get(position), property);
            }
        });

        ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport
                .OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemLongClicked(mReportDTOList.get(position),EDLListFragment.this);
                return false;
            }
        });

        serviceProperties = new ServiceProperties();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceProperties.getProperty(property.getmId(), new ICallback<PropertyDTO>() {

                    @Override
                    public void success(PropertyDTO propertyDTOs) {
                        mReportDTOList.clear();
                        mReportDTOList.addAll(propertyDTOs.getReportDTOList());
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(Throwable error) {
                    }

                    @Override
                    public void unauthorized() {

                    }

                });
            }
        });

        //System.out.println(addRoomFab.getId());
        if (addRoomFab != null) {
            addRoomFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    CreateEdlDialogFragment dialog = new CreateEdlDialogFragment();
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

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReportListCallback) {
            mCallback = (ReportListCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ReportListCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        serviceProperties.getProperty(property.getmId(), new ICallback<PropertyDTO>() {

            @Override
            public void success(PropertyDTO propertyDTOs) {
                mReportDTOList.clear();
                mReportDTOList.addAll(propertyDTOs.getReportDTOList());
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });
        super.onResume();
    }




    public static EDLListFragment newInstance(PropertyDTO property) {
        EDLListFragment f = new EDLListFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("property", property);
        f.setArguments(bdl);
        return f;
    }

    public interface ReportListCallback{
        void onItemSelected(ReportDTO reportDTO, PropertyDTO propertyDTO);

        void onItemLongClicked(ReportDTO reportDTO, EDLListFragment edlListFragment);
    }
}
