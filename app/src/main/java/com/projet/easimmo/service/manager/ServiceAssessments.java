package com.projet.easimmo.service.manager;

import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by victor on 08/05/2016.
 */
public class ServiceAssessments {

    private IServices mService;

    public ServiceAssessments() {
        mService = ServiceGenerator.createService(IServices.class);
    }


    public void postAssessment( String report_id,  String equipment_id,  String equipmentState, String comment, final ICallback<AssessmentDTO> callback) {
        final Call<AssessmentDTO> call = mService.postAssessment(report_id,equipment_id,equipmentState, comment);
        call.enqueue(new Callback<AssessmentDTO>() {
            @Override public void onResponse(Call<AssessmentDTO> call,
                                             Response<AssessmentDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<AssessmentDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

}
