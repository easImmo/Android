package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by victor on 24/04/2016.
 */
public class RoomDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("surface")
    private Double mSurface;
    @SerializedName("roomType")
    private String mType;
    @SerializedName("equipments")
    private List<EquipmentDTO> equipmentDTOList;

    public RoomDTO() {
    }

    public RoomDTO(String mId, Double mSurface, String roomTypeDTO, List<EquipmentDTO> equipmentDTOList) {

        this.mId = mId;
        this.mSurface = mSurface;
        this.mType = roomTypeDTO;
        this.equipmentDTOList = equipmentDTOList;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getRoomTypeDTO() {
        return mType;
    }

    public void setRoomTypeDTO(String roomTypeDTO) {
        this.mType = roomTypeDTO;
    }

    public Double getmSurface() {
        return mSurface;
    }

    public void setmSurface(Double mSurface) {
        this.mSurface = mSurface;
    }

    public List<EquipmentDTO> getEquipmentDTOList() {
        return equipmentDTOList;
    }

    public void setEquipmentDTOList(List<EquipmentDTO> equipmentDTOList) {
        this.equipmentDTOList = equipmentDTOList;
    }

   public String toString(){
       return this.getRoomTypeDTO();
   }
}
