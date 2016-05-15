package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.service.manager.ServiceRooms;

import java.util.ArrayList;


public class CreateRoomDialogFragment extends DialogFragment {

    private ServiceManager serviceManager;
    private ArrayList<String> roomTypeStringsList;

    public CreateRoomDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        serviceManager = new ServiceManager();

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_room_dialog, new FrameLayout(getActivity()), false);

        // Build dialog
        Dialog builder = new Dialog(getActivity());
        builder.setTitle("Creation Pièce");
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }
}
