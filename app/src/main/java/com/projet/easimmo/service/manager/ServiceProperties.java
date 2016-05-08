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
}
