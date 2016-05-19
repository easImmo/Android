package com.projet.easimmo.service.manager;

import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by victor on 08/05/2016.
 */
public class ServiceReports {

    private IServices mService;

    public ServiceReports() {
        mService = ServiceGenerator.createService(IServices.class);
    }


    public void postReport(String property_id, String comment, String type, final ICallback<ReportDTO> callback) {
        final Call<ReportDTO> call = mService.postReport(property_id,comment,type);
        call.enqueue(new Callback<ReportDTO>() {
            @Override public void onResponse(Call<ReportDTO> call,
                                             Response<ReportDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<ReportDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

    public void deleteReport(String report_id,final ICallback<ReportDTO> callback){
        final Call<ReportDTO> call = mService.deleteReport(report_id);
        call.enqueue(new Callback<ReportDTO>() {
            @Override public void onResponse(Call<ReportDTO> call,
                                             Response<ReportDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.success(response.body());
                }
                else if(response.code() == 401 || response.code() == 404){
                    callback.unauthorized();
                }
            }

            @Override public void onFailure(Call<ReportDTO> call, Throwable t) {
                callback.failure(t);
            }
        });
    }

}
