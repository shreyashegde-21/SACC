package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tenant_Signup_Form extends AppCompatActivity {
    private Button registerbutton;
    private TextView proffessiontextview;
    private RadioGroup radiogroup;
    private RadioButton student, working;
    private EditText fullname, age, phonenumber, email,
            aadharnumber, hometownaddress, username, password, confirmpassword,
            collegename, collegeaddress, companyname, companyaddress;
    private Spinner sexSpinner;

    AwesomeValidation validation;

    private FirebaseDatabase rootref;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_signup__form);

        fullname = (EditText) findViewById(R.id.fullname);
        age = (EditText) findViewById(R.id.age);

        sexSpinner = (Spinner) findViewById(R.id.sexSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter);

        phonenumber =(EditText) findViewById(R.id.phonenumber);
        email =(EditText) findViewById(R.id.email);
        aadharnumber =(EditText) findViewById(R.id.aadharnumber);
        hometownaddress =(EditText) findViewById(R.id.hometownaddress);
        proffessiontextview = (TextView) findViewById(R.id.proffessiontextview);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        student = findViewById(R.id.radiobutton1);
        working = findViewById(R.id.radiobutton2);
        collegename = findViewById(R.id.collegename);
        collegeaddress = findViewById(R.id.collegeaddress);
        companyname = findViewById(R.id.companyname);
        companyaddress = findViewById(R.id.companyaddress);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collegename.setEnabled(true);
                collegeaddress.setEnabled(true);
                companyname.setEnabled(false);
                companyaddress.setEnabled(false);
            }
        });
        working.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyname.setEnabled(true);
                companyaddress.setEnabled(true);
                collegename.setEnabled(false);
                collegeaddress.setEnabled(false);
            }
        });

        username =(EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        confirmpassword =(EditText) findViewById(R.id.confirmpassword);
        registerbutton = findViewById(R.id.registerButton);

        }

        private Boolean validatephonenumber(){
        String val = phonenumber.getText().toString();
        if(val.isEmpty()){
            phonenumber.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            phonenumber.setError(null);
            return true;
        }
    }

        private Boolean validateradiogroup(){
        int val = radiogroup.getCheckedRadioButtonId();
        if(val == -1){
            proffessiontextview.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            proffessiontextview.setError(null);
            return true;
        }
    }

        private Boolean validateradiogroupoptions(){
        String val1 = collegename.getText().toString();
        String val2= companyname.getText().toString();
        String val3 = collegeaddress.getText().toString();
        String val4 = companyaddress.getText().toString();

        if(!validateradiogroup()){
            return true;
        }
        else if(student.isChecked()) {
            if (val1.isEmpty()) {
                collegename.setError("This Field Cannot Be Left Empty.");
                return false;
            } else if (val3.isEmpty()) {
                collegeaddress.setError("This Field Cannot Be Left Empty.");
                return false;
            } else {
                collegename.setError(null);
                collegeaddress.setError(null);
                return true;
            }
        }
        else{
            if(val2.isEmpty()){
                companyname.setError("This Field Cannot Be Left Empty.");
                return false;
            }
            else if(val4.isEmpty()){
                companyaddress.setError("This Field Cannot Be Left Empty.");
                return false;
            }
            else{
                companyname.setError(null);
                companyaddress.setError(null);
                return true;
            }
        }

    }

        private Boolean validateusername(){
        String val = username.getText().toString();
        String nowhitespace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            username.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(val.length()>20){
            username.setError("Username Should Be Less Than 20 Characters.");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            username.setError("White Spaces Are Not Allowed.");
            return false;
        }
        else{
                username.setError(null);
                return true;
        }
    }

        private Boolean validatepassword(){
        String val = password.getText().toString();
        String passwordrequirements =   "^"+
                                        "(?=.*[a-zA-Z])"+
                                        "(?=.*[@#$%^&+=])"+
                                        ".{6,}"+
                                        "$";
        if(val.isEmpty()){
            password.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(!val.matches(passwordrequirements)){
            password.setError("password should contain 6-20 characters, atleast 1 upper-case and a lower-case alphabet,\n" +
                    "        atleast 1 numerical digit and a special character without any spaces.");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }


        public void Registeruser(View view){

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.fullname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        validation.addValidation(this, R.id.age, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        validation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        validation.addValidation(this, R.id.aadharnumber, ".{12,}", R.string.invalid_aadhar);
        validation.addValidation(this, R.id.hometownaddress, RegexTemplate.NOT_EMPTY, R.string.invalid_hometownaddress);
        validation.addValidation(this, R.id.confirmpassword, R.id.password,R.string.invalid_confirmpassword);

        if(!validation.validate() | !validatephonenumber() |!validateradiogroup() | !validateradiogroupoptions() |
                !validateusername() | !validatepassword()){
            return ;
        }

        rootref = FirebaseDatabase.getInstance();
        reference = rootref.getReference("Tenants");

        String NAME = fullname.getText().toString();
        int AGE = Integer.parseInt(age.getText().toString());
        String SEX = sexSpinner.getSelectedItem().toString();
        String PHONENUMBER = phonenumber.getText().toString();
        String EMAIL = email.getText().toString();
        String AADHARNUMBER = aadharnumber.getText().toString();
        String HOMETOWNADDRESS = hometownaddress.getText().toString();
        String USERNAME = username.getText().toString();
        String PASSWORD = password.getText().toString();

            Intent into = new Intent(Tenant_Signup_Form.this, OTP_Page.class);
            into.putExtra("PHONENUMBER",PHONENUMBER);
            startActivity(into);

        Tenant_helper_Class helperclass = new Tenant_helper_Class(NAME, AGE, SEX, PHONENUMBER, EMAIL, AADHARNUMBER,
                HOMETOWNADDRESS, PROFFESSION(),COLLEGEORCOMPANYNAME(), COLLEGEORCOMPANYADDRESS(),  USERNAME, PASSWORD);

        reference.child(NAME).setValue(helperclass);
    }


        private String COLLEGEORCOMPANYADDRESS() {
        if(student.isChecked()){
            return collegeaddress.getText().toString();
        }
        else{
            return companyaddress.getText().toString();
        }
    }

        private String COLLEGEORCOMPANYNAME() {
        if(student.isChecked()){
            return collegename.getText().toString();
        }
        else{
            return companyname.getText().toString();
        }

    }

        private String PROFFESSION() {
        if(student.isChecked()){
            return student.getText().toString();
        }
        else{
            return working.getText().toString();
        }
    }
}