package com.vbernard.easimmo;

import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.service.IServices;
import com.projet.easimmo.service.generator.ServiceGenerator;
import org.junit.Test;
import java.util.List;
import retrofit2.Call;


import static org.junit.Assert.*;

public class ExampleUnitTest {

    private List<EquipmentStateDTO> list;

    private IServices services;

    @Test
    public void test_services() throws Exception {
        services = ServiceGenerator.createService(IServices.class);
        Call<List<EquipmentStateDTO>> list = services.getEquipmentStates();
        List<EquipmentStateDTO> test = list.execute().body();
        assertEquals("Test taille de la liste ",3,test.size());
    }
}