package com.projet.easimmo.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.ReportDTO;

public class EDLActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edl);
        ReportDTO reportDTO = (ReportDTO) getIntent().getSerializableExtra("report");
        setTitle("Report_"+reportDTO.getmId().substring(0,9));
    }
}
