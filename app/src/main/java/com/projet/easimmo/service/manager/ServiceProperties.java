package com.projet.easimmo.service.manager;

import com.projet.easimmo.dto.PropertyDTO;
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
public class ServiceProperties {

    private IServices mService;

    public ServiceProperties() {
        mService = ServiceGenerator.createService(IServices.class);
    }

    public void getPropertiesUser(String idUser, final ICallback<List<PropertyDTO>> callback) {
        final Call<List<PropertyDTO>> call = mService.getPropertiesUser(idUser);
        call.enqueue(new Callback<List<PropertyDTO>>() {
            @Override public void onResponse(Call<List<PropertyDTO>> call,
                                             Response<List<PropertyDTO>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
            }

            @Override public void onFailure(Call<List<PropertyDTO>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("********************************************* FAIL");
                callback.failure(t);
            }
        });
    }

    public void postProperty(String user_id, String name, String addressLine1, String addressLine2, String zipCode, String city, final ICallback<PropertyDTO> callback) {
        System.out.println(name);
        final Call<PropertyDTO> call = mService.postProperty(user_id,name,addressLine1,addressLine2,zipCode,city);
        call.enqueue(new Callback<PropertyDTO>() {
            @Override public void onResponse(Call<PropertyDTO> call,
                                             Response<PropertyDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<PropertyDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }
}
