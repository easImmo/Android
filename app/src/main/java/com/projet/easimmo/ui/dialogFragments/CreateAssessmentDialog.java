package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.dto.RoomTypeDTO;
import com.projet.easimmo.ui.fragments.GeneralPropertyFragment;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAssessmentDialog extends DialogFragment {

    @Bind(R.id.room_type_spinner)
    Spinner _roomType;
    @Bind(R.id.equipment_type_spinner)
    Spinner _equipmentType;
    private ArrayList<RoomDTO> roomTypeStringsList;
    private ArrayList<EquipmentDTO> equipmentTypeStringsList;

    private PropertyDTO propertyDTO;

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
        final ArrayAdapter<RoomDTO> spinnerArrayAdapterRoom = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                roomTypeStringsList);

        final ArrayAdapter<EquipmentDTO> spinnerArrayAdapterEquipment = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                equipmentTypeStringsList);
        _roomType.setAdapter(spinnerArrayAdapterRoom);
        _equipmentType.setAdapter(spinnerArrayAdapterEquipment);

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

        return builder;
    }
}
