package com.projet.easimmo.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.display.EdlDetailDisplayDTO;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EDLDetailActivity extends AppCompatActivity {

    @Bind(R.id.equipment)
    TextView _equipment;

    @Bind(R.id.equipment_state)
    TextView _equipment_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edldetail);
        EdlDetailDisplayDTO detail = (EdlDetailDisplayDTO) getIntent().getSerializableExtra("detail");
        setTitle(detail.getRoomDTO().getRoomTypeDTO());
        ButterKnife.bind(this);
        _equipment.setText(" "+detail.getEquipmentDTO().getEquipmentTypeDTO());
        _equipment_state.setText(" "+detail.getAssessmentDTO().getEquipmentStateDTO());
    }
}
