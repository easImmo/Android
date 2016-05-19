package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 24/04/2016.
 */
public class ImageDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("filename")
    private String mFilename;
    @SerializedName("name")
    private String mName;

    public ImageDTO(){};
    public ImageDTO(String mId, String mFilename, String mName) {
        this.mId = mId;
        this.mFilename = mFilename;
        this.mName = mName;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmFilename() {
        return mFilename;
    }

    public void setmFilename(String mFilename) {
        this.mFilename = mFilename;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
