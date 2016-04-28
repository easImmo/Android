package com.projet.easimmo.service.manager;


import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
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
public class ServiceManager {

    private IServices mService;

    public ServiceManager() {
        mService = ServiceGenerator.createService(IServices.class);
    }

    public void getEquipmentStates(final ICallback<List<EquipmentStateDTO>> callback) {
        final Call<List<EquipmentStateDTO>> call = mService.getEquipmentStates();
        call.enqueue(new Callback<List<EquipmentStateDTO>>() {
            @Override public void onResponse(Call<List<EquipmentStateDTO>> call,
                                             Response<List<EquipmentStateDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<List<EquipmentStateDTO>> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void getRoomTypes(final ICallback<List<RoomTypeDTO>> callback) {
        final Call<List<RoomTypeDTO>> call = mService.getRoomTypes();
        call.enqueue(new Callback<List<RoomTypeDTO>>() {
            @Override public void onResponse(Call<List<RoomTypeDTO>> call, Response<List<RoomTypeDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<List<RoomTypeDTO>> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void getEquipmentTypes(final ICallback<List<EquipmentTypeDTO>> callback) {
        final Call<List<EquipmentTypeDTO>> call = mService.getEquipmentTypes();
        call.enqueue(new Callback<List<EquipmentTypeDTO>>() {
            @Override public void onResponse(Call<List<EquipmentTypeDTO>> call, Response<List<EquipmentTypeDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<List<EquipmentTypeDTO>> call, Throwable t) {
                callback.failure(t);
            }
        });
    }
}
