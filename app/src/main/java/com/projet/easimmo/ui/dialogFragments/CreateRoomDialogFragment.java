package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.service.manager.ServiceRooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CreateRoomDialogFragment extends DialogFragment {

    private ServiceManager serviceManager;
    private ServiceRooms serviceRooms;
    private ArrayList<String> roomTypeStringsList;

    @Bind(R.id.room_type_spinner)
    Spinner _roomType;
    @Bind(R.id.input_surface)
    EditText _surface;
    @Bind(R.id.btn_send)
    Button _sendBtn;

    String roomType, surface, idProperty;

    public CreateRoomDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        Bundle bundle=getArguments();
        idProperty = bundle.getString("property_id");
        serviceManager = new ServiceManager();
        roomTypeStringsList = new ArrayList<>();
        serviceRooms = new ServiceRooms();

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_room_dialog, new FrameLayout(getActivity()), false);

        ButterKnife.bind(this,view);


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                roomTypeStringsList);

        System.out.println(spinnerArrayAdapter);

        _roomType.setAdapter(spinnerArrayAdapter);

        serviceManager.getRoomTypes(new ICallback<List<RoomTypeDTO>>() {

            @Override
            public void success(List<RoomTypeDTO> roomTypeDTOList) {
                for(RoomTypeDTO roomTypeDTO : roomTypeDTOList){
                    roomTypeStringsList.add(roomTypeDTO.getmName());
                }
                Collections.sort(roomTypeStringsList);
                spinnerArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });

        _sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                roomType = _roomType.getSelectedItem().toString();
                surface = _surface.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Patientez...");
                progressDialog.show();

                new android.os.Handler().post(
                        new Runnable() {
                            public void run() {
                                serviceRooms.postPoom(idProperty, roomType, surface, new ICallback<RoomDTO>() {
                                    @Override
                                    public void success(RoomDTO roomDTO) {
                                        progressDialog.dismiss();
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

}
