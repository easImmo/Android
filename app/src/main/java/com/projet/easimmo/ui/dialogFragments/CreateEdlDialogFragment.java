package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.projet.easimmo.R;
import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.ReportDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceReports;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CreateEdlDialogFragment extends DialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }


    @Bind(R.id.btn_send_edl)
    Button _sendBtn;
    @Bind(R.id.switchButton)
    Switch _switchBtn;

    private ServiceReports serviceReports;

    private String edlType;

    public CreateEdlDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_edl_dialog, new FrameLayout(getActivity()), false);
        // Build dialog
        ButterKnife.bind(this,view);
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        edlType = "ENTRY";
        _switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edlType = "EXIT";
                }else{
                    edlType = "ENTRY";
                }
            }
        });
        serviceReports = new ServiceReports();

        _sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Patientez...");
                progressDialog.show();
                GlobalVar g = (GlobalVar)getActivity().getApplication();
                final PropertyDTO property = g.getPropertyDTO();

                new android.os.Handler().post(
                        new Runnable() {
                            public void run() {
                                serviceReports.postReport(property.getmId(), "", edlType, new ICallback<ReportDTO>() {
                                    @Override
                                    public void success(ReportDTO roomDTO) {
                                        progressDialog.dismiss();
                                        dismiss();
                                    }

                                    @Override
                                    public void failure(Throwable error) {
                                        error.printStackTrace();
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity().getBaseContext(), "Erreur lors de la création de l'état des lieux", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void unauthorized() {

                                    }
                                });
                            }
                        });
            }
        });

        return builder;
    }
}
