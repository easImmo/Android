package com.projet.easimmo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder>{

    private List<EquipmentDTO> mEquipmentList;

    public EquipmentAdapter(List<EquipmentDTO> mEquipmentList) {

        this.mEquipmentList = mEquipmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equipment_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EquipmentDTO equipment = mEquipmentList.get(position);
        holder.mNameTxv.setText(equipment.getEquipmentTypeDTO());
    }

    @Override
    public int getItemCount() {
        if(mEquipmentList != null) {
            return mEquipmentList.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTxv;

        public ViewHolder(View v) {
            super(v);
            mNameTxv = (TextView) v.findViewById(R.id.equipment_type);
        }
    }


}
