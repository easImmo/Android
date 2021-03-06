package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceAssessments;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.ui.fragments.GeneralPropertyFragment;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAssessmentDialog extends DialogFragment {

    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    @Bind(R.id.room_type_spinner)
    Spinner _roomType;
    @Bind(R.id.equipment_type_spinner)
    Spinner _equipmentType;
    @Bind(R.id.equipment_state)
    Spinner _equipmentState;
    @Bind(R.id.btn_send_assessment)
    Button _sendBtn;
    private ArrayList<RoomDTO> roomTypeStringsList;
    private ArrayList<EquipmentDTO> equipmentTypeStringsList;
    private ArrayList<EquipmentStateDTO> equipmentStateList;

    private PropertyDTO propertyDTO;
    private ServiceManager serviceManager;
    private ServiceAssessments serviceAssessments;

    public CreateAssessmentDialog()
    {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_assessment_dialog, new FrameLayout(getActivity()), false);
        GlobalVar g = (GlobalVar)getActivity().getApplication();
        propertyDTO = g.getPropertyDTO();

        ButterKnife.bind(this,view);
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        roomTypeStringsList = new ArrayList<>();
        equipmentTypeStringsList = new ArrayList<>();
        equipmentStateList = new ArrayList<>();
        final ArrayAdapter<RoomDTO> spinnerArrayAdapterRoom = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                roomTypeStringsList);

        final ArrayAdapter<EquipmentDTO> spinnerArrayAdapterEquipment = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                equipmentTypeStringsList);

        final ArrayAdapter<EquipmentStateDTO> spinnerArrayAdapterEquipmentState = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                equipmentStateList);
        _roomType.setAdapter(spinnerArrayAdapterRoom);
        _equipmentType.setAdapter(spinnerArrayAdapterEquipment);
        _equipmentState.setAdapter(spinnerArrayAdapterEquipmentState);

        roomTypeStringsList.addAll(propertyDTO.getRoomDTOList());
        Collections.sort(roomTypeStringsList, new Comparator<RoomDTO>() {
            @Override
            public int compare(final RoomDTO object1, final RoomDTO object2) {
                return object1.getRoomTypeDTO().compareTo(object2.getRoomTypeDTO());
            }
        } );
        spinnerArrayAdapterRoom.notifyDataSetChanged();

        equipmentTypeStringsList.addAll(roomTypeStringsList.get(0).getEquipmentDTOList());
        spinnerArrayAdapterEquipment.notifyDataSetChanged();

        serviceManager = new ServiceManager();
        serviceManager.getEquipmentStates(new ICallback<List<EquipmentStateDTO>>() {

            @Override
            public void success(List<EquipmentStateDTO> equipmentStateDTOs) {
                equipmentStateList.addAll(equipmentStateDTOs);
                spinnerArrayAdapterEquipmentState.notifyDataSetChanged();
            }

            @Override
            public void failure(Throwable error) {

            }

            @Override
            public void unauthorized() {

            }

        });

        _roomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            protected Adapter initializedAdapter=null;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if(initializedAdapter !=parentView.getAdapter() ) {
                    initializedAdapter = parentView.getAdapter();
                    return;
                }

                RoomDTO selected = (RoomDTO) parentView.getItemAtPosition(position);
                equipmentTypeStringsList.clear();
                equipmentTypeStringsList.addAll(selected.getEquipmentDTOList());
                spinnerArrayAdapterEquipment.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        serviceAssessments = new ServiceAssessments();
        _sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Patientez...");
                progressDialog.show();
                if(((EquipmentDTO)_equipmentType.getSelectedItem()) == null){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity().getBaseContext(), "L 'équipement selectionné n'est pas valide", Toast.LENGTH_LONG).show();
                }else {
                    new android.os.Handler().post(
                            new Runnable() {
                                public void run() {
                                    serviceAssessments.postAssessment((String) getArguments().get("report_id"), ((EquipmentDTO) _equipmentType.getSelectedItem()).getmId(), ((EquipmentStateDTO) _equipmentState.getSelectedItem()).getmName(), "", new ICallback<AssessmentDTO>() {
                                        @Override
                                        public void success(AssessmentDTO assessmentDTO) {
                                            progressDialog.dismiss();
                                            dismiss();
                                        }

                                        @Override
                                        public void failure(Throwable error) {
                                            error.printStackTrace();
                                            progressDialog.dismiss();
                                            Toast.makeText(getActivity().getBaseContext(), "Erreur lors de la création du sinistre", Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void unauthorized() {

                                        }
                                    });
                                }
                            });
                }
            }
        });

        return builder;
    }
}
