package com.projet.easimmo.service.manager;

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
public class ServiceRooms {

    private IServices mService;

    public ServiceRooms() {
        mService = ServiceGenerator.createService(IServices.class);
    }


    public void postPoom(String property_id, String roomType, String surface, final ICallback<RoomDTO> callback) {
        final Call<RoomDTO> call = mService.postRoom(property_id,roomType,surface);
        call.enqueue(new Callback<RoomDTO>() {
            @Override public void onResponse(Call<RoomDTO> call,
                                             Response<RoomDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<RoomDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

}
