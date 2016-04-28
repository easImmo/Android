package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 24/04/2016.
 */
public class RoomDTO {

    @SerializedName("_id")
    private String mId;
    @SerializedName("surface")
    private Double mSurface;
    @SerializedName("roomType")
    private RoomTypeDTO roomTypeDTO;

    public RoomDTO() {
    }

    public RoomDTO(String mId, Double mSurface, RoomTypeDTO roomTypeDTO) {

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

    public RoomTypeDTO getRoomTypeDTO() {
        return roomTypeDTO;
    }

    public void setRoomTypeDTO(RoomTypeDTO roomTypeDTO) {
        this.roomTypeDTO = roomTypeDTO;
    }

    public Double getmSurface() {
        return mSurface;
    }

    public void setmSurface(Double mSurface) {
        this.mSurface = mSurface;
    }
}
