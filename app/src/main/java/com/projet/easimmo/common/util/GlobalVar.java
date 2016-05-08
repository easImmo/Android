package com.projet.easimmo.common.util;

import android.app.Application;

/**
 * Created by victor on 09/05/2016.
 */
public class GlobalVar extends Application {
    private String idUser;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
