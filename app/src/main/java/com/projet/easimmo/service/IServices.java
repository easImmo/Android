package com.projet.easimmo.service;

import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Victor on 26/04/2016.
 */
public interface IServices {

    @GET("properties/user/{user_id}")
    Call<List<PropertyDTO>> getPropertiesUser(@Path("user_id") String id);

    @GET("properties/{property_id}")
    Call<PropertyDTO> getProperty(@Path("property_id") String property_id);

    @FormUrlEncoded
    @POST("properties")
    Call<PropertyDTO> postProperty(@Field("user_id") String user_id, @Field("name") String name, @Field("addressLine1") String addressLine1,
                                   @Field("addressLine2") String addressLine2,@Field("zipCode") String zipCode,@Field("city") String city);

    @FormUrlEncoded
    @PUT("properties")
    Call<PropertyDTO> putProperty(@Field("property_id") String user_id, @Field("name") String name, @Field("addressLine1") String addressLine1,
                                   @Field("addressLine2") String addressLine2,@Field("zipCode") String zipCode,@Field("city") String city);

    @GET("reports/{report_id}")
    Call<ReportDTO> getReport(@Path("report_id") String report_id);

    @GET("equipmentStates")
    Call<List<EquipmentStateDTO>> getEquipmentStates();

    @GET("roomTypes")
    Call<List<RoomTypeDTO>> getRoomTypes();

    @GET("EquipmentTypes")
    Call<List<EquipmentTypeDTO>> getEquipmentTypes();

    @FormUrlEncoded
    @POST("users/login")
    Call<UserDTO> login(@Field("email") String email, @Field("password") String password);

    @GET("equipments/{equipment_id}")
    Call<EquipmentDTO> getEquipment(@Path("equipment_id") String equipment_id);



}

