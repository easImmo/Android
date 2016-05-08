package com.projet.easimmo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;

import java.util.List;

/**
 * Created by victor on 08/05/2016.
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder>{

    private List<PropertyDTO> mPropertyList;

    public PropertyAdapter(List<PropertyDTO> mPropertyList) {

        this.mPropertyList = mPropertyList;
        System.out.println("Size****************************: "+ mPropertyList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.property_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PropertyDTO property = mPropertyList.get(position);
        holder.mNameTxv.setText(property.getmName());
        holder.mCityTxv.setText(property.getmCity());
    }

    @Override
    public int getItemCount() {
        if(mPropertyList != null) {
            return mPropertyList.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTxv;
        private TextView mCityTxv;

        public ViewHolder(View v) {
            super(v);
            mNameTxv = (TextView) v.findViewById(R.id.property_name);
            mCityTxv = (TextView) v.findViewById(R.id.property_city);
        }
    }


}
