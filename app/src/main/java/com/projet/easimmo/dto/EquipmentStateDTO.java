package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 24/04/2016.
 */
public class EquipmentStateDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public EquipmentStateDTO() {
    }

    public EquipmentStateDTO(String mId, String mName) {
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
