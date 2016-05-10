package com.projet.easimmo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 24/04/2016.
 */
public class ReportDTO implements Serializable {

    @SerializedName("_id")
    private String mId;
    @SerializedName("property")
    private PropertyDTO mProperty;
    @SerializedName("assessments")
    private List<AssessmentDTO> assessmentDTOList;
    @SerializedName("createdAt")
    private Date mCreated_at;
    private Date mUpdated_at;


    public ReportDTO() {
    }

    public ReportDTO(PropertyDTO mProperty, List<AssessmentDTO> assessmentDTOList, Date mCreated_at, Date mUpdated_at, String mId) {
        this.mProperty = mProperty;
        this.assessmentDTOList = assessmentDTOList;
        this.mCreated_at = mCreated_at;
        this.mUpdated_at = mUpdated_at;
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public List<AssessmentDTO> getAssessmentDTOList() {
        return assessmentDTOList;
    }

    public void setAssessmentDTOList(List<AssessmentDTO> assessmentDTOList) {
        this.assessmentDTOList = assessmentDTOList;
    }

    public PropertyDTO getmProperty() {
        return mProperty;
    }

    public void setmProperty(PropertyDTO mProperty) {
        this.mProperty = mProperty;
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

}
