package com.travelmate;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
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

import com.travelmate.data.LoginRegisterSQLHelper;

public class RegisterActivity extends AppCompatActivity {


    EditText fullName, email, username, password, confirmpassword, age;
    RadioGroup rg_gender, rg_registeras;
    RadioButton male, female, tourist, guider;
    Button signUp;
    String selectedRdBtnGenderText, selectedRdBtnRegisterasText;
    LoginRegisterSQLHelper loginRegisterSQLHelper;
    int selectedRdBtnGenderID,selectedRdBtnRegisterasID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        loginRegisterSQLHelper = new LoginRegisterSQLHelper(this);
        fullName = (EditText) findViewById(R.id.signup_input_fullname);
        email = (EditText) findViewById(R.id.signup_input_email);
        username = (EditText) findViewById(R.id.signup_input_username);
        password = (EditText) findViewById(R.id.signup_input_password);
        confirmpassword = (EditText) findViewById(R.id.signup_input_confirmpassword);
        age = (EditText) findViewById(R.id.signup_input_age);
        rg_gender = (RadioGroup) findViewById(R.id.gender_radio_group);
        rg_registeras = (RadioGroup) findViewById(R.id.register_as_radio_group);
        signUp = (Button)findViewById(R.id.btn_signup);
         selectedRdBtnGenderID = rg_gender.getCheckedRadioButtonId();
         selectedRdBtnRegisterasID = rg_registeras.getCheckedRadioButtonId();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (fullName.getText().toString().equals("") || email.getText().toString().equals("") || username.getText().toString().equals("") ||
                        password.getText().toString().equals("") || confirmpassword.getText().toString().equals("") || age.getText().toString().equals("")) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Alert Dialog");
                    alertDialog.setMessage("Please fill all the fields!");
                    AlertDialog alertDialogMain = alertDialog.create();
                    alertDialogMain.show();
                } else {
                    String str_fullname = fullName.getText().toString();
                    String str_email = email.getText().toString();
                    String str_username = username.getText().toString();
                    String str_password = "";
                    String str_age = age.getText().toString();
                    String strgender = selectedRdBtnGenderText;
                    String strregisterAs = selectedRdBtnRegisterasText;
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        str_password = password.getText().toString();
                    } else {
                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(RegisterActivity.this);
                        alertDialog1.setTitle("Alert Dialog");
                        alertDialog1.setMessage("Password doesn't match!");
                        AlertDialog alertDialogMain1 = alertDialog1.create();
                        alertDialogMain1.show();
                    }
                    if (!(str_fullname.equals("") || str_email.equals("") || str_username.equals("") || str_password.equals("") || str_age.equals("") || strgender.equals("") || strregisterAs.equals(""))) {
                        loginRegisterSQLHelper.addNewUser(fullName.getText().toString(),
                                username.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                selectedRdBtnGenderText,
                                selectedRdBtnRegisterasText,
                                Integer.parseInt(age.getText().toString()));

                    }
                }
            }
        });



    }
}