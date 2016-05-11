package com.projet.easimmo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.RoomDTO;

import java.text.SimpleDateFormat;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{

    private List<RoomDTO> mRoomList;

    public RoomAdapter(List<RoomDTO> mRoomList) {

        this.mRoomList = mRoomList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomDTO room = mRoomList.get(position);
        holder.mNameTxv.setText(room.getRoomTypeDTO());
    }

    @Override
    public int getItemCount() {
        if(mRoomList != null) {
            return mRoomList.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTxv;

        public ViewHolder(View v) {
            super(v);
            mNameTxv = (TextView) v.findViewById(R.id.room_type);
        }
    }


}
