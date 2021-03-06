package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.PropertyAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceProperties;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class PropertiesFragment extends Fragment {

    private List<PropertyDTO> mPropertyDTOList;
    @Bind(R.id.property_list)
    RecyclerView mRecyclerView;
    private PropertyAdapter mAdapter;
    private ServiceProperties serviceProperties;

    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private PropertyListCallback mCallback;
    private String idUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_properties, container, false);
        GlobalVar g = (GlobalVar)getActivity().getApplication();
        idUser = g.getIdUser();
        ButterKnife.bind(this,rootView);
        mRecyclerView.setHasFixedSize(false);

        System.out.println("PropertiesFragment***************** "+g.getIdUser());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);


        //System.out.println("********************************************* "+idUser);
        serviceProperties = new ServiceProperties();
        serviceProperties.getPropertiesUser(idUser,new ICallback<List<PropertyDTO>>() {

            @Override public void success(List<PropertyDTO> propertyDTOs) {
                mPropertyDTOList = new ArrayList<PropertyDTO>();
                mPropertyDTOList.addAll(propertyDTOs);
                mAdapter = new PropertyAdapter(mPropertyDTOList);
                mRecyclerView.addItemDecoration(
                        new DividerItemDecoration(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });

        //System.out.println("*******************TAILLE LISTE mPropertyDTOList2: "+mPropertyDTOList.size());

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                mCallback.onItemSelected(mPropertyDTOList.get(position));
            }
        });


        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                serviceProperties.getPropertiesUser(idUser,new ICallback<List<PropertyDTO>>() {

                    @Override public void success(List<PropertyDTO> propertyDTOs) {
                        mPropertyDTOList.clear();
                        mPropertyDTOList.addAll(propertyDTOs);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PropertyListCallback) {
            mCallback = (PropertyListCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PropertyListCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        serviceProperties.getPropertiesUser(idUser, new ICallback<List<PropertyDTO>>() {

            @Override
            public void success(List<PropertyDTO> propertyDTOs) {

                Boolean isNew = false;
                if(mPropertyDTOList == null){
                    isNew = true;
                    mPropertyDTOList = new ArrayList<PropertyDTO>();
                }
                mPropertyDTOList.clear();
                mPropertyDTOList.addAll(propertyDTOs);
                if(isNew == false) {
                    mAdapter.notifyDataSetChanged();
                }
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



    public interface PropertyListCallback{
        void onItemSelected(PropertyDTO propertyDTO);
    }
}
