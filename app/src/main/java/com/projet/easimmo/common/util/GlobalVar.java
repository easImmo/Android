package com.projet.easimmo.common.util;

import android.app.Application;

import com.projet.easimmo.dto.PropertyDTO;

/**
 * Created by victor on 09/05/2016.
 */
public class GlobalVar extends Application {
    private String idUser;
    private PropertyDTO propertyDTO;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }
}
