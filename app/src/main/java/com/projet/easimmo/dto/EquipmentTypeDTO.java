package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 24/04/2016.
 */
public class EquipmentTypeDTO {

    @SerializedName("_id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public EquipmentTypeDTO() {
    }

    public EquipmentTypeDTO(String mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
