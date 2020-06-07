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

public class Owner_Signup_Form extends AppCompatActivity {
    private Button ownerregisterbutton;
    AwesomeValidation ownervalidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__signup__form);

        Spinner ownersexSpinner = (Spinner) findViewById(R.id.ownersexSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownersexSpinner.setAdapter(adapter);

        ownervalidation = new AwesomeValidation(ValidationStyle.BASIC);
        ownervalidation.addValidation(this, R.id.ownerfullname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        ownervalidation.addValidation(this, R.id.ownerage, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        ownervalidation.addValidation(this, R.id.ownerphonenumber, "[5-9]{1}[0-9]{9}$", R.string.invalid_phonenumber);
        ownervalidation.addValidation(this, R.id.owneremail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        ownervalidation.addValidation(this, R.id.owneraadharnumber, ".{12,}", R.string.invalid_aadhar);
        ownervalidation.addValidation(this, R.id.ownerusername, RegexTemplate.NOT_EMPTY, R.string.invalid_username);
        ownervalidation.addValidation(this, R.id.ownerpassword, ".{6,}"+"(?=\\s+$)"+"(?=.*[0-9])"
                +"(?=.*[a-z])"+"(?=.*[A-Z])"+"(?=.*[@#$%^&+=])",R.string.invalid_password);
        ownervalidation.addValidation(this, R.id.ownerconfirmpassword, R.id.ownerpassword,R.string.invalid_confirmpassword);

        ownerregisterbutton = findViewById(R.id.ownerregisterButton);
        ownerregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ownervalidation.validate() ){
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}