package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 24/04/2016.
 */
public class RoomDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("surface")
    private Double mSurface;
    @SerializedName("roomType")
    private String roomTypeDTO;

    public RoomDTO() {
    }

    public RoomDTO(String mId, Double mSurface, String roomTypeDTO) {

        this.mId = mId;
        this.mSurface = mSurface;
        this.roomTypeDTO = roomTypeDTO;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getRoomTypeDTO() {
        return roomTypeDTO;
    }

    public void setRoomTypeDTO(String roomTypeDTO) {
        this.roomTypeDTO = roomTypeDTO;
    }

    public Double getmSurface() {
        return mSurface;
    }

    public void setmSurface(Double mSurface) {
        this.mSurface = mSurface;
    }
}
