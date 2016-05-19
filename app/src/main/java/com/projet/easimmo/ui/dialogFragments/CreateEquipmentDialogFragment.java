package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.EquipmentTypeDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceEquipment;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.service.manager.ServiceRooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CreateEquipmentDialogFragment extends DialogFragment {

    private ServiceManager serviceManager;
    private ServiceEquipment serviceEquipment;
    private ArrayList<String> equipmentTypeStringsList;
    private CreateEquipmentCallback mCreateEquipmentCallback;

    @Bind(R.id.equipment_type_spinner)
    Spinner _equipmentType;
    @Bind(R.id.btn_send)
    Button _sendBtn;

    String equipmentType, idRoom;


    public CreateEquipmentDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        Bundle bundle=getArguments();
        idRoom = bundle.getString("room_id");
        serviceManager = new ServiceManager();
        equipmentTypeStringsList = new ArrayList<>();
        serviceEquipment = new ServiceEquipment();

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_equipment_dialog, new FrameLayout(getActivity()), false);

        ButterKnife.bind(this,view);


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                equipmentTypeStringsList);

        System.out.println(spinnerArrayAdapter);

        _equipmentType.setAdapter(spinnerArrayAdapter);

        serviceManager.getEquipmentTypes(new ICallback<List<EquipmentTypeDTO>>() {

            @Override
            public void success(List<EquipmentTypeDTO> equipmentTypeDTOList) {
                for(EquipmentTypeDTO equipmentTypeDTO: equipmentTypeDTOList){
                    equipmentTypeStringsList.add(equipmentTypeDTO.getmName());
                }
                Collections.sort(equipmentTypeStringsList);
                spinnerArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });

        final CreateEquipmentCallback finalMCreateEquipmentCallback = mCreateEquipmentCallback;
        _sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                equipmentType = _equipmentType.getSelectedItem().toString();

                final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Patientez...");
                progressDialog.show();

                new android.os.Handler().post(
                        new Runnable() {
                            public void run() {
                                serviceEquipment.postEquipment(idRoom, equipmentType, new ICallback<EquipmentDTO>() {
                                    @Override
                                    public void success(EquipmentDTO equipmentDTO) {
                                        progressDialog.dismiss();
                                        finalMCreateEquipmentCallback.onPOSTComplete();
                                        getDialog().dismiss();
                                    }

                                    @Override
                                    public void failure(Throwable error) {
                                        error.printStackTrace();
                                        progressDialog.dismiss();
                                        getDialog().dismiss();
                                    }

                                    @Override
                                    public void unauthorized() {

                                    }
                                });
                            }
                        });
            }
        });

        // Build dialog
        Dialog builder = new Dialog(getActivity());
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateEquipmentCallback) {
            mCreateEquipmentCallback = (CreateEquipmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ReportListCallback");
        }
    }

    public interface CreateEquipmentCallback{
        void onPOSTComplete();
    }

}
