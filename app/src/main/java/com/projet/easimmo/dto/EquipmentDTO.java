package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by victor on 24/04/2016.
 */
public class EquipmentDTO {

    @SerializedName("_" +
            "id")
    private String mId;
    @SerializedName("room")
    private RoomDTO roomDTO;
    @SerializedName("equipmentType")
    private EquipmentTypeDTO equipmentTypeDTO;

    public EquipmentDTO() {
    }

    public EquipmentDTO(String mId, RoomDTO roomDTO, EquipmentTypeDTO equipmentTypeDTO) {
        this.mId = mId;
        this.roomDTO = roomDTO;
        this.equipmentTypeDTO = equipmentTypeDTO;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public EquipmentTypeDTO getEquipmentTypeDTO() {
        return equipmentTypeDTO;
    }

    public void setEquipmentTypeDTO(EquipmentTypeDTO equipmentTypeDTO) {
        this.equipmentTypeDTO = equipmentTypeDTO;
    }
}
