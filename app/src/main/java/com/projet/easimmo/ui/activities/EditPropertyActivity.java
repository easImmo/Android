package com.projet.easimmo.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceProperties;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditPropertyActivity extends AppCompatActivity {

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout _coordinatorLayout;
    @Bind(R.id.input_name)
    EditText _name;
    @Bind(R.id.input_addressLine1)
    EditText _addressLine1;
    @Bind(R.id.input_addressLine2)
    EditText _addressLine2;
    @Bind(R.id.input_city)
    EditText _city;
    @Bind(R.id.input_zip)
    EditText _zip;
    @Bind(R.id.btn_send)
    Button _sendBtn;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    String idUser,name, addressLine1, addressLine2, city, zip;

    PropertyDTO property;

    private ServiceProperties serviceProperties;
    private boolean EDIT_MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GlobalVar g = (GlobalVar)getApplication();
        idUser = g.getIdUser();
        EDIT_MODE = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Logement");


        property = (PropertyDTO) getIntent().getSerializableExtra("property");


        if(property != null){
            EDIT_MODE = true;
        }

        System.out.println("EDIT MODE : " + EDIT_MODE);

        if(EDIT_MODE) {
            _name.setText(property.getmName());
            _addressLine1.setText(property.getmAddressLine1());
            _addressLine2.setText(property.getmAddressLine2());
            _city.setText(property.getmCity());
            _zip.setText(property.getmZipCode().toString());
        }



        _sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name = _name.getText().toString();
                addressLine1 = _addressLine1.getText().toString();
                addressLine2 = _addressLine2.getText().toString();
                city = _city.getText().toString();
                zip = _zip.getText().toString();

                if (EDIT_MODE) putProperty();
                else postProperty();
            }
        });
        serviceProperties = new ServiceProperties();
    }

    private void postProperty(){

        final ProgressDialog progressDialog = new ProgressDialog(EditPropertyActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Patientez...");
        progressDialog.show();

        new android.os.Handler().post(
                new Runnable() {
                    public void run() {
                        serviceProperties.postProperty(idUser, name, addressLine1, addressLine2, zip, city, new ICallback<PropertyDTO>() {
                            @Override
                            public void success(PropertyDTO propertyDTO) {
                                progressDialog.dismiss();
                                finish();
                            }

                            @Override
                            public void failure(Throwable error) {
                                error.printStackTrace();
                                progressDialog.dismiss();
                                Snackbar.make(_coordinatorLayout, "Erreur lors de la création du lot", Snackbar.LENGTH_LONG).show();
                            }

                            @Override
                            public void unauthorized() {

                            }
                        });
                    }
                });
    }

    private void putProperty(){

        final ProgressDialog progressDialog = new ProgressDialog(EditPropertyActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Patientez...");
        progressDialog.show();

        new android.os.Handler().post(
                new Runnable() {
                    public void run() {
                        serviceProperties.putProperty(property.getmId(), name, addressLine1, addressLine2, zip, city, new ICallback<PropertyDTO>() {
                            @Override
                            public void success(PropertyDTO propertyDTO) {
                                progressDialog.dismiss();
                                finish();
                            }

                            @Override
                            public void failure(Throwable error) {
                                error.printStackTrace();
                                progressDialog.dismiss();
                                Snackbar.make(_coordinatorLayout, "Erreur lors de la création du lot", Snackbar.LENGTH_LONG).show();
                            }

                            @Override
                            public void unauthorized() {

                            }
                        });
                    }
                });
    }



}
