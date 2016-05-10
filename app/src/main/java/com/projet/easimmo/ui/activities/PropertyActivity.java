package com.projet.easimmo.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.ui.fragments.EDLListFragment;
import com.projet.easimmo.ui.fragments.GeneralPropertyFragment;
import com.projet.easimmo.ui.fragments.RoomListFragment;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager_property);

        PropertyDTO property;
        Bundle extras = getIntent().getExtras();
        property = (PropertyDTO) getIntent().getSerializableExtra("property");

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), property));

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_property);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(nameProperty);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private PropertyDTO property;

        public MyPagerAdapter(FragmentManager fm, PropertyDTO property) {
            super(fm);
            this.property = property;
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0:
                    GeneralPropertyFragment g = new GeneralPropertyFragment();
                    return g.newInstance(property);
                case 1:
                    RoomListFragment r = new RoomListFragment();
                    return r.newInstance(property.getmId());
                case 2:
                    EDLListFragment edl = new EDLListFragment();
                    return edl.newInstance(property.getmId());
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
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
