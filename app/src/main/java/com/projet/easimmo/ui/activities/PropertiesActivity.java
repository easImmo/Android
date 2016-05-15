package com.projet.easimmo.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.ui.fragments.PropertiesFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PropertiesActivity extends AppCompatActivity implements PropertiesFragment.PropertyListCallback{

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mes logements");

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
        Intent intent = new Intent(this, PropertyActivity.class);
        intent.putExtra("property", propertyDTO);
        GlobalVar g = (GlobalVar)getApplication();
        g.setPropertyDTO(propertyDTO);
        startActivity(intent);

        System.out.println(g.getIdUser());
    }
}
