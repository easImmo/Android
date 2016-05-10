package com.projet.easimmo.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.projet.easimmo.R;
import com.projet.easimmo.ui.fragments.EDLListFragment;
import com.projet.easimmo.ui.fragments.GeneralProperyFragment;
import com.projet.easimmo.ui.fragments.RoomListFragment;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager_property);

        String idProperty, nameProperty;
        Bundle extras = getIntent().getExtras();
        idProperty= extras.getString("idProperty");
        nameProperty = extras.getString("nameProperty");

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), idProperty));

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_property);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(nameProperty);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private String mIdProperty;

        public MyPagerAdapter(FragmentManager fm, String idProperty) {
            super(fm);
            this.mIdProperty = idProperty;
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0:
                    GeneralProperyFragment g = new GeneralProperyFragment();
                    return g.newInstance(mIdProperty);
                case 1:
                    RoomListFragment r = new RoomListFragment();
                    return r.newInstance(mIdProperty);
                case 2:
                    EDLListFragment edl = new EDLListFragment();
                    return edl.newInstance(mIdProperty);
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
