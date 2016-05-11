package com.projet.easimmo.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceEquipment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by victor on 08/05/2016.
 */
public class EDLEquipmentAdapter extends RecyclerView.Adapter<EDLEquipmentAdapter.ViewHolder>{

    private List<AssessmentDTO> mAssessmentDTOs;
    private ServiceEquipment serviceEquipment;
    private EquipmentDTO equipmentDTO;

    public EDLEquipmentAdapter(List<AssessmentDTO> mAssessmentDTOs) {

        this.mAssessmentDTOs = mAssessmentDTOs;
        //System.out.println("Size****************************: "+ mPropertyList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edlequipment_small_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        serviceEquipment = new ServiceEquipment();
        AssessmentDTO assessmentDTO = mAssessmentDTOs.get(position);

        serviceEquipment.getEquipment(assessmentDTO.getEquipmentDTO(), new ICallback<EquipmentDTO>() {

            @Override
            public void success(EquipmentDTO equipment) {
               equipmentDTO = equipment;
                setObjects(holder);
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });


    }

    @Override
    public int getItemCount() {
        if(mAssessmentDTOs != null) {
            return mAssessmentDTOs.size();
        }else{
            return 0;
        }
    }

    public void setObjects(ViewHolder holder){
        holder.mNameEquipmentTxv.setText(equipmentDTO.getEquipmentTypeDTO());
        holder.mNameRommTxv.setText(equipmentDTO.getRoomDTO().getRoomTypeDTO());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameEquipmentTxv;
        private TextView mNameRommTxv;

        public ViewHolder(View v) {
            super(v);
            mNameEquipmentTxv = (TextView) v.findViewById(R.id.equipment_name);
            mNameRommTxv = (TextView) v.findViewById(R.id.room_name);
        }
    }


}
