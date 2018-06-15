package com.travelmate.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.travelmate.R;

import com.travelmate.data.LoginRegisterRepository;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogIn;
    LoginRegisterRepository loginRegisterRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        loginRegisterRepository = new LoginRegisterRepository(this);
        btnLogIn = (Button)findViewById(R.id.btn_login);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginRegisterRepository.isUser(etUsername.getText().toString(),etPassword.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(),UsersActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(LoginActivity.this);
                    alertDialog1.setTitle("Alert Dialog");
                    alertDialog1.setMessage("Username or password is incorrect!");
                    AlertDialog alertDialogMain1 = alertDialog1.create();
                    alertDialogMain1.show();
                }
            }
        });



    }


}
