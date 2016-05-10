package com.projet.easimmo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.ui.activities.EditPropertyActivity;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GeneralPropertyFragment extends Fragment {

    @Bind(R.id.address_content)
    TextView _addressContent;

    @Bind(R.id.address2_content)
    TextView _address2Content;

    @Bind(R.id.city_content)
    TextView _cityContent;

    @Bind(R.id.zip_content)
    TextView _zipContent;

    private PropertyDTO property;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general_property, container, false);
        ButterKnife.bind(this, v);

        property = (PropertyDTO) getArguments().getSerializable("property");

        assert property != null;
        _addressContent.setText(property.getmAddressLine1());
        _address2Content.setText(property.getmAddressLine2());
        _cityContent.setText(property.getmCity());
        _zipContent.setText(String.format(Locale.FRANCE,"%d",property.getmZipCode()));


        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.edit_fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(fab.getContext(),EditPropertyActivity.class);
                    intent.putExtra("property", property);
                    startActivity(intent);
                }
            });
        }

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    public static GeneralPropertyFragment newInstance(PropertyDTO property) {
        GeneralPropertyFragment f = new GeneralPropertyFragment();

        Bundle bdl = new Bundle(1);
        bdl.putSerializable("property", property);
        f.setArguments(bdl);
        return f;
    }

}
