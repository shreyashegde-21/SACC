package com.example.pglocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Tenant_Signup_Form extends AppCompatActivity {
    private Button registerbutton;
    private RadioButton radiobutton1, radiobutton2;
    private EditText collegename, collegeaddress, companyname, companyaddress;
    AwesomeValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_signup__form);

        Spinner sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter);

        radiobutton1 = findViewById(R.id.radiobutton1);
        radiobutton2 = findViewById(R.id.radiobutton2);
        collegename = findViewById(R.id.collegename);
        collegeaddress = findViewById(R.id.collegeaddress);
        companyname = findViewById(R.id.companyname);
        companyaddress = findViewById(R.id.companyaddress);
        radiobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collegename.setEnabled(true);
                collegeaddress.setEnabled(true);
                companyname.setEnabled(false);
                companyaddress.setEnabled(false);
            }
        });
        radiobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyname.setEnabled(true);
                companyaddress.setEnabled(true);
                collegename.setEnabled(false);
                collegeaddress.setEnabled(false);
            }
        });

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.fullname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        validation.addValidation(this, R.id.age, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        validation.addValidation(this, R.id.phonenumber, "[5-9]{1}[0-9]{9}$", R.string.invalid_phonenumber);
        validation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        validation.addValidation(this, R.id.aadharnumber, ".{12,}", R.string.invalid_aadhar);
        validation.addValidation(this, R.id.hometownaddress, RegexTemplate.NOT_EMPTY, R.string.invalid_hometownaddress);
        validation.addValidation(this, R.id.username, RegexTemplate.NOT_EMPTY, R.string.invalid_username);
        validation.addValidation(this, R.id.password, ".{6,}"+"(?=\\s+$)"+"(?=.*[0-9])"
                +"(?=.*[a-z])"+"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])",R.string.invalid_password);
        validation.addValidation(this, R.id.confirmpassword, R.id.password,R.string.invalid_confirmpassword);

        registerbutton = findViewById(R.id.registerButton);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation.validate() ){
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}