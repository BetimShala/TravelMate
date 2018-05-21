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
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginRegisterActivity extends AppCompatActivity {

    EditText fullName, email, username, password, confirmpassword, age;
    RadioGroup rg_gender,rg_registeras;
    RadioButton male, female, tourist, guider;
    Button signUp;
    String selectedRdBtnGenderText, selectedRdBtnRegisterasText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        fullName = (EditText) findViewById(R.id.signup_input_fullname);
        email = (EditText) findViewById(R.id.signup_input_email);
        username = (EditText)findViewById(R.id.signup_input_username);
        password = (EditText) findViewById(R.id.signup_input_password);
        confirmpassword = (EditText) findViewById(R.id.signup_input_confirmpassword);
        age = (EditText) findViewById(R.id.signup_input_age);
        rg_gender = (RadioGroup) findViewById(R.id.gender_radio_group);
        rg_registeras = (RadioGroup) findViewById(R.id.register_as_radio_group);

        int selectedRdBtnGenderID = rg_gender.getCheckedRadioButtonId();
        int selectedRdBtnRegisterasID = rg_registeras.getCheckedRadioButtonId();

        // If nothing is selected from Radio Group, then it return -1
        if (selectedRdBtnGenderID != -1) {

            RadioButton selectedRdBtnGender = (RadioButton) findViewById(selectedRdBtnGenderID);
            selectedRdBtnGenderText = selectedRdBtnGender.getText().toString();

        } else {

        }

        if (selectedRdBtnRegisterasID != -1) {

            RadioButton selectedRdBtnRegisteras = (RadioButton) findViewById(selectedRdBtnRegisterasID);
            selectedRdBtnRegisterasText = selectedRdBtnRegisteras.getText().toString();

        } else {

        }


    }
    public void onRegister(View view){
        String str_fullname = fullName.getText().toString();
        String str_email = email.getText().toString();
        String str_username = username.getText().toString();
        String str_password = "";
        if(password.getText().toString().equals(confirmpassword.getText().toString())){
            str_password = password.getText().toString();
        }
        else{

        }
        String str_age = age.getText().toString();
        String strgender = selectedRdBtnGenderText;
        String strregisterAs = selectedRdBtnRegisterasText;
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_fullname, str_email, str_username, str_password, str_age, strgender, strregisterAs);
    }

}
