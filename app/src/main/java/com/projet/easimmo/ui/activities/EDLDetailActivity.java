package com.projet.easimmo.ui.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.CustomAdapter;
import com.projet.easimmo.dto.display.EdlDetailDisplayDTO;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

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

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_image);
        CirclePageIndicator mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);

        PagerAdapter adapter = new CustomAdapter(EDLDetailActivity.this);

        viewPager.setAdapter(adapter);

        mIndicator.setViewPager(viewPager);
    }


}
