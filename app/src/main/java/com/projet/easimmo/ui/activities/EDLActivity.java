package com.projet.easimmo.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.AssessmentDTO;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.display.EdlDetailDisplayDTO;
import com.projet.easimmo.service.manager.ServiceEquipment;
import com.projet.easimmo.ui.fragments.EDLEquipmentFragment;
import com.projet.easimmo.ui.fragments.GeneralEDLFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EDLActivity extends AppCompatActivity implements EDLEquipmentFragment.EDLEquipmentCallback{

    @Bind(R.id.viewPager_edl)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edl);
        ReportDTO reportDTO = (ReportDTO) getIntent().getSerializableExtra("report");
        PropertyDTO propertyDTO = (PropertyDTO) getIntent().getSerializableExtra("property");
        if(reportDTO.getmType() != null){
            setTitle(reportDTO.getmType()+"_"+reportDTO.getmId().substring(0,9));
        }else {
            setTitle("Report_" + reportDTO.getmId().substring(0, 9));
        }
        ButterKnife.bind(this);
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(GeneralEDLFragment.newInstance(reportDTO));
        fList.add(EDLEquipmentFragment.newInstance(reportDTO,propertyDTO));
        pager.setAdapter(new EDLPagerAdapter(getSupportFragmentManager(), fList));
    }

    private class EDLPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public EDLPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragments = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Général";
                case 1:
                    return "Sinistre(s)";
            }
            return null;
        }
    }

    @Override
    public void onItemSelected(EdlDetailDisplayDTO edl) {
        Intent intent = new Intent(this, EDLDetailActivity.class);
        intent.putExtra("detail", edl);
        startActivity(intent);
    }
}
