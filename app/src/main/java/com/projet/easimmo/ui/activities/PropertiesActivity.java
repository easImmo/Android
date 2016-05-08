package com.projet.easimmo.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.ui.fragments.PropertiesFragment;

public class PropertiesActivity extends AppCompatActivity implements PropertiesFragment.PropertyListCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mes logements");


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fab.getContext(),EditPropertyActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onItemSelected(PropertyDTO propertyDTO) {

    }
}
