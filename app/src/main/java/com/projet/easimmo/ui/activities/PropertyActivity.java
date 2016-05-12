package com.projet.easimmo.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.ui.fragments.EDLListFragment;
import com.projet.easimmo.ui.fragments.GeneralPropertyFragment;
import com.projet.easimmo.ui.fragments.RoomListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertyActivity extends AppCompatActivity implements EDLListFragment.ReportListCallback, RoomListFragment.RoomListCallback{

    @Bind(R.id.name_content)
    TextView _nameContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager_property);
        ButterKnife.bind(this);

        PropertyDTO property = (PropertyDTO) getIntent().getSerializableExtra("property");


        if(property == null) {
            GlobalVar g = (GlobalVar)getApplication();
            property = g.getPropertyDTO();
        }
        _nameContent.setText(property.getmName());

        setTitle("Visualisation de logement");
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(GeneralPropertyFragment.newInstance(property));
        fList.add(RoomListFragment.newInstance(property));
        fList.add(EDLListFragment.newInstance(property));

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fList));

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_property);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(nameProperty);
    }




    @Override
    public void onItemSelected(ReportDTO reportDTO, PropertyDTO propertyDTO) {
        Intent intent = new Intent(this, EDLActivity.class);
        intent.putExtra("report", reportDTO);
        intent.putExtra("property", propertyDTO);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(RoomDTO roomDTO) {
        System.out.println("Olel");
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
                case 2:
                    return "Etat des lieux";
            }
            return null;
        }
    }
}
