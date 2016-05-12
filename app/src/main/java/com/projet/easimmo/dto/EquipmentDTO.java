package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 24/04/2016.
 */
public class EquipmentDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("equipmentType")
    private String equipmentTypeDTO;

    public EquipmentDTO() {
    }

    public EquipmentDTO(String mId, String equipmentTypeDTO) {
        this.mId = mId;
        this.equipmentTypeDTO = equipmentTypeDTO;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getEquipmentTypeDTO() {
        return equipmentTypeDTO;
    }

    public void setEquipmentTypeDTO(String equipmentTypeDTO) {
        this.equipmentTypeDTO = equipmentTypeDTO;
    }
}
