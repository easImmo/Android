package com.projet.easimmo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projet.easimmo.common.util.GlobalVar;
import com.projet.easimmo.dto.EquipmentStateDTO;
import com.projet.easimmo.dto.UserDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceManager;
import com.projet.easimmo.service.manager.ServiceUser;
import com.projet.easimmo.ui.activities.PropertiesActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;

    String email,password;

    private ServiceUser serviceUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.projet.easimmo.R.layout.activity_main);

        ButterKnife.bind(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        serviceUser = new ServiceUser();

    }

    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentification...");
        progressDialog.show();

         email = _emailText.getText().toString();
         password = _passwordText.getText().toString();

        new android.os.Handler().post(
                new Runnable() {
                    public void run() {

                        System.out.println("TEST ENTREE");
                        serviceUser.login(email, password, new ICallback<UserDTO>() {

                            @Override public void success(UserDTO userDTO) {
                                progressDialog.dismiss();
                                System.out.println(userDTO);
                                System.out.println(userDTO.getmId());
                                onLoginSuccess(userDTO.getmId());
                            }

                            @Override public void failure(Throwable error) {
                                progressDialog.dismiss();
                                onLoginFailed();
                            }

                            @Override
                            public void unauthorized() {
                                progressDialog.dismiss();
                                onLoginFailed();
                            }
                        });

                    }
                });
    }

    public void onLoginSuccess(String id) {
        _loginButton.setEnabled(true);
        finish();
        Intent intent = new Intent(this, PropertiesActivity.class);
        //intent.putExtra("userId", id);
        GlobalVar g = (GlobalVar)getApplication();
        g.setIdUser(id);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Erreur de connection", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Adresse mail invalide");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.projet.easimmo.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.projet.easimmo.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
