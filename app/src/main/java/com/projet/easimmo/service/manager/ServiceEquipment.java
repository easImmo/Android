package com.projet.easimmo.service.manager;

import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by victor on 08/05/2016.
 */
public class ServiceEquipment {

    private IServices mService;

    public ServiceEquipment() {
        mService = ServiceGenerator.createService(IServices.class);
    }

    public void postEquipment(String room_id, String equipmentType, final ICallback<EquipmentDTO> callback) {
        final Call<EquipmentDTO> call = mService.postEquipment(room_id,equipmentType);
        call.enqueue(new Callback<EquipmentDTO>() {
            @Override public void onResponse(Call<EquipmentDTO> call,
                                             Response<EquipmentDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<EquipmentDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void getEquipment(String idEquipment, final ICallback<EquipmentDTO> callback) {
        final Call<EquipmentDTO> call = mService.getEquipment(idEquipment);
        call.enqueue(new Callback<EquipmentDTO>() {
            @Override public void onResponse(Call<EquipmentDTO> call,
                                             Response<EquipmentDTO> response) {

                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<EquipmentDTO> call, Throwable t) {
                t.printStackTrace();
                //System.out.println("********************************************* FAIL");
                callback.failure(t);
            }
        });
    }


}
