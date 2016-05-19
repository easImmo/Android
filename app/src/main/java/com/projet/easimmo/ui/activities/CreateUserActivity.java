package com.projet.easimmo.ui.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.UserDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateUserActivity extends AppCompatActivity {

    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_signup)
    Button _signupBtn;
    @Bind(R.id.link_login)
    TextView _loginLink;

    private ServiceUser serviceUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);

        _signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CreateUserActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creation...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        serviceUser = new ServiceUser();

        new android.os.Handler().post(
                new Runnable() {
                    public void run() {
                        serviceUser.create(email, password, new ICallback<UserDTO>() {

                            @Override public void success(UserDTO userDTO) {
                                progressDialog.dismiss();
                                System.out.println(userDTO);
                                System.out.println(userDTO.getmId());
                                onSignupSuccess();
                            }

                            @Override public void failure(Throwable error) {
                                progressDialog.dismiss();
                                onSignupFailed();
                            }

                            @Override
                            public void unauthorized() {
                                progressDialog.dismiss();
                                onSignupFailed();
                            }
                        });
                    }
                });
    }


    public void onSignupSuccess() {
        _signupBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Erreur de connexion", Toast.LENGTH_LONG).show();

        _signupBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("nom doit faire plus de 3 caractères");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Adresse email invalide");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Le mot de passe doit etre entre 4 et 10 caractères");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
