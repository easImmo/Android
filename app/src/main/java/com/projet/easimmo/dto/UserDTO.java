package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 24/04/2016.
 */
public class UserDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("lastName")
    private String mlastName;
    @SerializedName("email")
    private String memail;
    @SerializedName("admin")
    private Boolean madmin;

    public UserDTO(){}

    public UserDTO(String mId, String mFirstName, String mlastName, String memail, Boolean madmin) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mlastName = mlastName;
        this.memail = memail;
        this.madmin = madmin;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public Boolean getMadmin() {
        return madmin;
    }

    public void setMadmin(Boolean madmin) {
        this.madmin = madmin;
    }

    public String getMlastName() {
        return mlastName;
    }

    public void setMlastName(String mlastName) {
        this.mlastName = mlastName;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }


}
