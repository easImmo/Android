package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 24/04/2016.
 */
public class PropertyDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("adressLine1")
    private String mAddressLine1;
    @SerializedName("adressLine2")
    private String mAddressLine2;
    @SerializedName("zipCode")
    private Integer mZipCode;
    @SerializedName("city")
    private String mCity;
    @SerializedName("user")
    private String mIdUser;
    @SerializedName("rooms")
    private List<RoomDTO> roomDTOList;
    @SerializedName("reports")
    private List<ReportDTO> reportDTOList;
    private Date mCreated_at;
    private Date mUpdated_at;

    public PropertyDTO() {
    }

    public PropertyDTO(String mId, String mName, String mAddressLine1, String mAddressLine2, Integer mZipCode, String mCity, String mIdUser, List<RoomDTO> roomDTOList, Date mCreated_at, Date mUpdated_at, List<ReportDTO> reportDTOList) {
        this.mId = mId;
        this.mName = mName;
        this.mAddressLine1 = mAddressLine1;
        this.mAddressLine2 = mAddressLine2;
        this.mZipCode = mZipCode;
        this.mCity = mCity;
        this.mIdUser = mIdUser;
        this.roomDTOList = roomDTOList;
        this.mCreated_at = mCreated_at;
        this.mUpdated_at = mUpdated_at;
        this.reportDTOList = reportDTOList;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmAddressLine1() {
        return mAddressLine1;
    }

    public void setmAddressLine1(String mAddressLine1) {
        this.mAddressLine1 = mAddressLine1;
    }

    public String getmAddressLine2() {
        return mAddressLine2;
    }

    public void setmAddressLine2(String mAddressLine2) {
        this.mAddressLine2 = mAddressLine2;
    }

    public Integer getmZipCode() {
        return mZipCode;
    }

    public void setmZipCode(Integer mZipCode) {
        this.mZipCode = mZipCode;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public List<RoomDTO> getRoomDTOList() {
        return roomDTOList;
    }

    public void setRoomDTOList(List<RoomDTO> roomDTOList) {
        this.roomDTOList = roomDTOList;
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


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmIdUser() {
        return mIdUser;
    }

    public void setmIdUser(String mIdUser) {
        this.mIdUser = mIdUser;
    }

    public List<ReportDTO> getReportDTOList() {
        return reportDTOList;
    }

    public void setReportDTOList(List<ReportDTO> reportDTOList) {
        this.reportDTOList = reportDTOList;
    }
}
