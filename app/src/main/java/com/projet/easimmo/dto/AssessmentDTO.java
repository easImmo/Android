package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 24/04/2016.
 */
public class AssessmentDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("equipment")
    private String equipmentDTO;
    @SerializedName("equipmentState")
    private String equipmentStateDTO;
    @SerializedName("comment")
    private String mComment;
    private Date mCreated_at;
    private Date mUpdated_at;
    @SerializedName("images")
    private List<ImageDTO> imageDTOList;

    public AssessmentDTO() {
    }

    public AssessmentDTO(String mId, String equipmentDTO, String equipmentStateDTO, String mComment, Date mCreated_at, Date mUpdated_at,List<ImageDTO> imageDTOList) {
        this.mId = mId;
        this.equipmentDTO = equipmentDTO;
        this.equipmentStateDTO = equipmentStateDTO;
        this.mComment = mComment;
        this.mCreated_at = mCreated_at;
        this.mUpdated_at = mUpdated_at;
        this.imageDTOList = imageDTOList;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getEquipmentDTO() {
        return equipmentDTO;
    }

    public void setEquipmentDTO(String equipmentDTO) {
        this.equipmentDTO = equipmentDTO;
    }

    public String getEquipmentStateDTO() {
        return equipmentStateDTO;
    }

    public void setEquipmentStateDTO(String equipmentStateDTO) {
        this.equipmentStateDTO = equipmentStateDTO;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public Date getmCreated_at() {
        return mCreated_at;
    }

    public void setmCreated_at(Date mCreated_at) {
        this.mCreated_at = mCreated_at;
    }

    public Date getmUpdated_at() {
        return mUpdated_at;
    }

    public void setmUpdated_at(Date mUpdated_at) {
        this.mUpdated_at = mUpdated_at;
    }

    public List<ImageDTO> getImageDTOList() {
        return imageDTOList;
    }

    public void setImageDTOList(List<ImageDTO> imageDTOList) {
        this.imageDTOList = imageDTOList;
    }
}
