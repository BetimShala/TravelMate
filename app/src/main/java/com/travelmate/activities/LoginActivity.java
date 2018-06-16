package com.travelmate.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.travelmate.R;

import com.travelmate.data.LoginRegisterRepository;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogIn;
    String username, password;
    Context ctx = this;
    LoginRegisterRepository loginRegisterRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void Login(View view) {
        loginRegisterRepository = new LoginRegisterRepository(ctx);
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        Cursor CR = loginRegisterRepository.getInfo(loginRegisterRepository);
        CR.moveToFirst();
        String fullname = "", email = "";
        boolean loginStatus = false;
        do {
            if (username.equals(CR.getString(1)) && password.equals(CR.getString(3))) {
                loginStatus = true;
                fullname = CR.getString(0);
                email = CR.getString(2);
            }
        } while (CR.moveToNext());
        if (loginStatus) {
            Intent i = new Intent(LoginActivity.this, UsersActivity.class);
            i.putExtra("fullname", fullname);
            i.putExtra("email", email);
            startActivity(i);
        }else
        {

        }
    }
}


