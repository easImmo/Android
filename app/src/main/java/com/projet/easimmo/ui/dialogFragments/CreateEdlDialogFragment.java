package com.projet.easimmo.ui.dialogFragments;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projet.easimmo.R;


public class CreateEdlDialogFragment extends DialogFragment {

    public CreateEdlDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_create_edl_dialog, new FrameLayout(getActivity()), false);

        // Retrieve layout elements
        TextView title = (TextView) view.findViewById(R.id.title_test_edl_create);

        // Set values
        title.setText("Not perfect yet");

        // Build dialog
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }
}
