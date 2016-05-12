package com.projet.easimmo.dto.display;

import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;

import java.io.Serializable;


/**
 * Created by victor on 12/05/2016.
 */
public class EdlDetailDisplayDTO implements Serializable{

    private PropertyDTO propertyDTO;
    private AssessmentDTO assessmentDTO;
    private EquipmentDTO equipmentDTO;
    private RoomDTO roomDTO;


    public EdlDetailDisplayDTO(){}

    public EdlDetailDisplayDTO(PropertyDTO propertyDTO, AssessmentDTO assessmentDTO, EquipmentDTO equipmentDTO, RoomDTO roomDTO) {
        this.propertyDTO = propertyDTO;
        this.assessmentDTO = assessmentDTO;
        this.equipmentDTO = equipmentDTO;
        this.roomDTO = roomDTO;
    }

    public AssessmentDTO getAssessmentDTO() {
        return assessmentDTO;
    }

    public void setAssessmentDTO(AssessmentDTO assessmentDTO) {
        this.assessmentDTO = assessmentDTO;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public EquipmentDTO getEquipmentDTO() {
        return equipmentDTO;
    }

    public void setEquipmentDTO(EquipmentDTO equipmentDTO) {
        this.equipmentDTO = equipmentDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }
}
