package com.projet.easimmo.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.service.manager.ServiceProperties;
import com.projet.easimmo.service.manager.ServiceRooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditRoomActivity extends AppCompatActivity {

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout _coordinatorLayout;
    @Bind(R.id.room_type_spinner)
    Spinner _roomType;
    @Bind(R.id.input_surface)
    EditText _surface;
    @Bind(R.id.btn_send)
    Button _sendBtn;

    String roomType, surface, idUser;

    PropertyDTO property;

    private ServiceManager serviceManager;
    private ServiceRooms serviceRooms;
    private ArrayList<String> roomTypeStringsList;
    private boolean EDIT_MODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        serviceManager = new ServiceManager();
        GlobalVar g = (GlobalVar)getApplication();
        idUser = g.getIdUser();
        roomTypeStringsList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pièce");
        ButterKnife.bind(this);
        serviceRooms = new ServiceRooms();

        property = (PropertyDTO) getIntent().getSerializableExtra("property");


        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                roomTypeStringsList);

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

                final ProgressDialog progressDialog = new ProgressDialog(EditRoomActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Patientez...");
                progressDialog.show();

                new android.os.Handler().post(
                        new Runnable() {
                            public void run() {
                                serviceRooms.postPoom(property.getmId(), roomType, surface, new ICallback<RoomDTO>() {
                                    @Override
                                    public void success(RoomDTO roomDTO) {
                                        progressDialog.dismiss();
                                        finish();
                                    }

                                    @Override
                                    public void failure(Throwable error) {
                                        error.printStackTrace();
                                        progressDialog.dismiss();
                                        Snackbar.make(_coordinatorLayout, "Erreur lors de la création du de la piece", Snackbar.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void unauthorized() {

                                    }
                                });
                            }
                        });
            }
        });
    }




}
