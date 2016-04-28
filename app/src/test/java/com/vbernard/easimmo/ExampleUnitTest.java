package com.vbernard.easimmo;

import android.util.Log;

import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.System;
import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void test_services() throws Exception {
        ServiceManager serviceManager = new ServiceManager();
        serviceManager.getEquipmentStates(new ICallback<List<EquipmentStateDTO>>() {
            @Override
            public void success(List<EquipmentStateDTO> equipmentStateDTO) {
                System.out.println("TEST");
                for(EquipmentStateDTO dto : equipmentStateDTO){
                    System.out.println(dto.getmName());
                }
            }

            @Override
            public void failure(Throwable error) {
                Log.w("", error.getMessage());
            }
        });
    }
}