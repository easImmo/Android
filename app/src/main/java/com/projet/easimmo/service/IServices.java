package com.projet.easimmo.service;

import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomTypeDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Victor on 26/04/2016.
 */
public interface IServices {

    @GET("properties/user/{user_id}")
    Call<List<PropertyDTO>> getPropertiesUser(@Path("user_id") String id);

    @GET("properties/{property_id}")
    Call<PropertyDTO> getProperty(@Path("property_id") String property_id);

    @GET("reports/{report_id}")
    Call<ReportDTO> getReport(@Path("report_id") String report_id);

    @GET("equipmentStates")
    Call<List<EquipmentStateDTO>> getEquipmentStates();

    @GET("roomTypes")
    Call<List<RoomTypeDTO>> getRoomTypes();

    @GET("EquipmentTypes")
    Call<List<EquipmentTypeDTO>> getEquipmentTypes();

}

