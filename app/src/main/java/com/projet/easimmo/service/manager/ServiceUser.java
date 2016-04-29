package com.projet.easimmo.service.manager;


import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.dto.UserDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Victor on 25/04/2016.
 */
public class ServiceUser {

    private IServices mService;

    public ServiceUser() {
        mService = ServiceGenerator.createService(IServices.class);
    }

    public void login(String email, String password, final ICallback<UserDTO> callback) {
        final Call<UserDTO> call = mService.login(email,password);
        call.enqueue(new Callback<UserDTO>() {
            @Override public void onResponse(Call<UserDTO> call,
                                             Response<UserDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<UserDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

}
