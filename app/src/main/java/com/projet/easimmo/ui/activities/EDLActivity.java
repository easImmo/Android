package com.projet.easimmo.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.ui.fragments.GeneralEDLFragment;

import java.util.ArrayList;
import java.util.List;

public class EDLActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edl);
        ReportDTO reportDTO = (ReportDTO) getIntent().getSerializableExtra("report");
        setTitle("Report_"+reportDTO.getmId().substring(0,9));

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager_edl);

        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(GeneralEDLFragment.newInstance(reportDTO));
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
                    return "Pièces";
            }
            return null;
        }
    }
}
