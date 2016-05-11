package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.RoomAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceProperties;
import com.projet.easimmo.ui.activities.EditPropertyActivity;
import com.projet.easimmo.ui.activities.EditRoomActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RoomListFragment extends Fragment {
    private PropertyDTO property;
    private List<RoomDTO> mRoomDTOList;
    private RecyclerView mRecyclerView;
    private RoomAdapter mAdapter;
    private ServiceProperties serviceProperties;

    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private RoomListCallback mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.room_list);

        mRecyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRoomDTOList = new ArrayList<>();
        property = (PropertyDTO) getArguments().getSerializable("property");

        mRoomDTOList.addAll(property.getRoomDTOList());
        mAdapter = new RoomAdapter(mRoomDTOList);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        ButterKnife.bind(this,rootView);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mRoomDTOList.get(position));
            }
        });

        serviceProperties = new ServiceProperties();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceProperties.getProperty(property.getmId(), new ICallback<PropertyDTO>() {

                    @Override
                    public void success(PropertyDTO propertyDTOs) {
                        mRoomDTOList.clear();
                        mRoomDTOList.addAll(propertyDTOs.getRoomDTOList());
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

        final FloatingActionButton addRoomFab = (FloatingActionButton) rootView.findViewById(R.id.addRoomFab);
        System.out.println(addRoomFab.getId());
        if (addRoomFab != null) {
            addRoomFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(addRoomFab.getContext(),EditRoomActivity.class);
                    intent.putExtra("property", property);
                    startActivity(intent);
                }
            });
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomListCallback) {
            mCallback = (RoomListCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RoomListCallback");
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
                mRoomDTOList.clear();
                mRoomDTOList.addAll(propertyDTOs.getRoomDTOList());
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




    public static RoomListFragment newInstance(PropertyDTO property) {
        RoomListFragment f = new RoomListFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("property", property);
        f.setArguments(bdl);
        return f;
    }

    public interface RoomListCallback{
        void onItemSelected(RoomDTO roomDTO);
    }
}
