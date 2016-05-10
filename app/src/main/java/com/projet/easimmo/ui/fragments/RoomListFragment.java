package com.projet.easimmo.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projet.easimmo.R;


public class RoomListFragment extends Fragment {
    private String mIdProperty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_room_list, container, false);

        return v;
    }

    public RoomListFragment newInstance(String idProperty) {
        RoomListFragment f = new RoomListFragment();
        this.mIdProperty = idProperty;
        return f;
    }
}
