package com.projet.easimmo.service.manager;

import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.ImageDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public void postImage(MultipartBody.Part file, RequestBody assessment_id, final ICallback<ImageDTO> callback) {
        final Call<ImageDTO> call = mService.upload(file,assessment_id);
        call.enqueue(new Callback<ImageDTO>() {
            @Override public void onResponse(Call<ImageDTO> call,
                                             Response<ImageDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
                System.out.println(response.code());
                System.out.println(response.body());
            }

            @Override public void onFailure(Call<ImageDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void deleteAssessment(String report_id,final ICallback<AssessmentDTO> callback){
        final Call<AssessmentDTO> call = mService.deleteAssessment(report_id);
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
                System.out.println(t);
                callback.failure(t);
            }
        });
    }
}
