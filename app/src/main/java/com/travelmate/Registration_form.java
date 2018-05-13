package com.travelmate;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registration_form extends AppCompatActivity {

    EditText fullName, email, password, age;
    RadioButton male, female, tourist, guider;
    Button signUp;
    String gender, registerAs;
    LoginRegisterSQLHelper loginRegisterSQLHelper;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        loginRegisterSQLHelper = new LoginRegisterSQLHelper(getApplicationContext());
        fullName = (EditText)findViewById(R.id.signup_input_fullname);
        email = (EditText)findViewById(R.id.signup_input_email);
        password = (EditText)findViewById(R.id.signup_input_password);
        age = (EditText)findViewById(R.id.signup_input_age);
        male = (RadioButton)findViewById(R.id.male_radio_btn);
        female = (RadioButton)findViewById(R.id.female_radio_btn);
        tourist = (RadioButton)findViewById(R.id.tourist_radio_btn);
        guider = (RadioButton)findViewById(R.id.guider_radio_btn);
        signUp = (Button)findViewById(R.id.btn_signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFullName = fullName.getText().toString();
                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();
                String strAge = age.getText().toString();
                if(male.isSelected())
                    gender = "Male";
                else if(female.isSelected())
                    gender = "Female";

                if(tourist.isSelected())
                    registerAs = "Tourist";
                else if(guider.isSelected())
                    registerAs = "Guider";

/*                if ((TextUtils.isEmpty(strFullName)) || (TextUtils.isEmpty(strEmail)) || (TextUtils.isEmpty(strPassword))
            || (TextUtils.isEmpty(strAge)) || (TextUtils.isEmpty(gender)) || (TextUtils.isEmpty(registerAs))){
                    Toast.makeText(Registration_form.this,"Please Fill All the Fields!", Toast.LENGTH_LONG).show();

                }
                else {*/
                    loginRegisterSQLHelper.addNewUser(fullName.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            gender,registerAs,
                            Integer.parseInt(age.getText().toString()));

            }
        });

    }

}
