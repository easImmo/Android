package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;


public class RoomListFragment extends Fragment {
    private PropertyDTO mProperty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_room_list, container, false);
        mProperty = (PropertyDTO) getArguments().getSerializable("property");
        return v;
    }

    public static RoomListFragment newInstance(PropertyDTO propertyDTO) {
        RoomListFragment f = new RoomListFragment();
        Bundle bdl = new Bundle(1);
        bdl.putSerializable("property", propertyDTO);
        f.setArguments(bdl);
        return f;
    }
}
