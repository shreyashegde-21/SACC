package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Tenant_Signup_Form extends AppCompatActivity {
    private Button tspregisterbutton;
    private TextView tspproffessiontextview;
    private RadioGroup tspradiogroup;
    private RadioButton tspstudent, tspworking;
    private EditText tspfullname, tspage, tspphonenumber, tspemail,
            tspaadharnumber, tsphometownaddress, tspusername, tsppassword, tspconfirmpassword,
            tspcollegename, tspcollegeaddress, tspcompanyname, tspcompanyaddress;
    private Spinner tspsexSpinner;
    private ProgressBar tspprogressbar;

    AwesomeValidation tspvalidation;

    private FirebaseDatabase fData;
    private DatabaseReference fRef;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_signup__form);
        fAuth = FirebaseAuth.getInstance();

        tspfullname = (EditText) findViewById(R.id.tspfullname);
        tspage = (EditText) findViewById(R.id.tspage);

        tspsexSpinner = (Spinner) findViewById(R.id.tspsexSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tspsexSpinner.setAdapter(adapter);

        tspphonenumber =(EditText) findViewById(R.id.tspphonenumber);
        tspemail =(EditText) findViewById(R.id.tspemail);
        tspaadharnumber =(EditText) findViewById(R.id.tspaadharnumber);
        tsphometownaddress =(EditText) findViewById(R.id.tsphometownaddress);
        tspproffessiontextview = (TextView) findViewById(R.id.tspproffessiontextview);
        tspradiogroup = (RadioGroup) findViewById(R.id.tspradiogroup);
        tspstudent = findViewById(R.id.tspradiobuttonstudent);
        tspworking = findViewById(R.id.tspradiobuttonworking);
        tspcollegename = findViewById(R.id.tspcollegename);
        tspcollegeaddress = findViewById(R.id.tspcollegeaddress);
        tspcompanyname = findViewById(R.id.tspcompanyname);
        tspcompanyaddress = findViewById(R.id.tspcompanyaddress);

        tspstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tspcollegename.setEnabled(true);
                tspcollegeaddress.setEnabled(true);
                tspcompanyname.setEnabled(false);
                tspcompanyaddress.setEnabled(false);
            }
        });
        tspworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tspcompanyname.setEnabled(true);
                tspcompanyaddress.setEnabled(true);
                tspcollegename.setEnabled(false);
                tspcollegeaddress.setEnabled(false);
            }
        });

        tspusername =(EditText) findViewById(R.id.tspusername);
        tsppassword =(EditText) findViewById(R.id.tsppassword);
        tspconfirmpassword =(EditText) findViewById(R.id.tspconfirmpassword);
        tspregisterbutton = (Button) findViewById(R.id.tspregisterButton);
        tspprogressbar = (ProgressBar) findViewById(R.id.tspprogressbar);

        }

        private Boolean validatephonenumber(){
        String val = tspphonenumber.getText().toString();
        if(val.isEmpty()){
            tspphonenumber.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            tspphonenumber.setError(null);
            return true;
        }
    }

        private Boolean validateradiogroup(){
        int val = tspradiogroup.getCheckedRadioButtonId();
        if(val == -1){
            tspproffessiontextview.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            tspproffessiontextview.setError(null);
            return true;
        }
    }

        private Boolean validateradiogroupoptions(){
        String val1 = tspcollegename.getText().toString();
        String val2= tspcompanyname.getText().toString();
        String val3 = tspcollegeaddress.getText().toString();
        String val4 = tspcompanyaddress.getText().toString();

        if(!validateradiogroup()){
            return true;
        }
        else if(tspstudent.isChecked()) {
            if (val1.isEmpty()) {
                tspcollegename.setError("This Field Cannot Be Left Empty.");
                return false;
            } else if (val3.isEmpty()) {
                tspcollegeaddress.setError("This Field Cannot Be Left Empty.");
                return false;
            } else {
                tspcollegename.setError(null);
                tspcollegeaddress.setError(null);
                return true;
            }
        }
        else{
            if(val2.isEmpty()){
                tspcompanyname.setError("This Field Cannot Be Left Empty.");
                return false;
            }
            else if(val4.isEmpty()){
                tspcompanyaddress.setError("This Field Cannot Be Left Empty.");
                return false;
            }
            else{
                tspcompanyname.setError(null);
                tspcompanyaddress.setError(null);
                return true;
            }
        }

    }

        private Boolean validateusername(){
        String val = tspusername.getText().toString();
        String nowhitespace = "\\A\\w{6,20}\\z";
        if(val.isEmpty()){
            tspusername.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(val.length()<6){
            tspusername.setError("Username Should Be Between 6-20 Characters.");
            return false;
        }
        else if(val.length()>20){
            tspusername.setError("Username Should Be Between 6-20 Characters.");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            tspusername.setError("White Spaces Are Not Allowed.");
            return false;
        }
        else{
                tspusername.setError(null);
                return true;
        }
    }

        private Boolean validatepassword() {
        String val = tsppassword.getText().toString();
        String passwordrequirements =   "^"+
                                        "(?=.*[a-zA-Z])"+
                                        "(?=.*[@#$%^&+=])"+
                                        ".{6,}"+
                                        "$";
        if(val.isEmpty()){
            tsppassword.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(!val.matches(passwordrequirements)){
            tsppassword.setError("password should contain 6-20 characters, atleast one any case alphabet" +
                    "and a special character without any spaces.");
            return false;
        }
        else{
            tsppassword.setError(null);
            return true;
        }
    }

        public void Registeruser(View view) {

        tspprogressbar.setVisibility(View.VISIBLE);

        tspvalidation = new AwesomeValidation(ValidationStyle.BASIC);
        tspvalidation.addValidation(this, R.id.tspfullname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        tspvalidation.addValidation(this, R.id.tspage, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        tspvalidation.addValidation(this, R.id.tspemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        tspvalidation.addValidation(this, R.id.tspaadharnumber, ".{12,}", R.string.invalid_aadhar);
        tspvalidation.addValidation(this, R.id.tsphometownaddress, RegexTemplate.NOT_EMPTY, R.string.invalid_hometownaddress);
        tspvalidation.addValidation(this, R.id.tspconfirmpassword, R.id.tsppassword,R.string.invalid_confirmpassword);

        if(!tspvalidation.validate() | !validatephonenumber() |!validateradiogroup() | !validateradiogroupoptions() |
                !validateusername() | !validatepassword()){
            tspprogressbar.setVisibility(View.INVISIBLE);
            return ;
        }

        createUser();
    }

        private void createUser() {

        String EMAIL = tspemail.getText().toString();
        String PASSWORD = tsppassword.getText().toString();

        fAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    tspprogressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Tenant_Signup_Form.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    fUser = fAuth.getCurrentUser();
                    if(fUser!=null){
                        fUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Verification Mail Sent.\nPlease verify your mail to login.", Toast.LENGTH_LONG).show();
                                    sendUserData();
                                    fAuth.signOut();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Verification Mail couldn't be sent.\nTry Again!", Toast.LENGTH_SHORT).show();
                                    //user should be returned along with deleting his account.
                                }
                            }
                        });
                    }
                }
            }
        });

    }

        private void sendUserData() {

        fData = FirebaseDatabase.getInstance();
        fRef = fData.getReference("Users");
        fAuth = FirebaseAuth.getInstance();
        String NAME = tspfullname.getText().toString();
        int AGE = Integer.parseInt(tspage.getText().toString());
        String SEX = tspsexSpinner.getSelectedItem().toString();
        String PHONENUMBER = tspphonenumber.getText().toString();
        String EMAIL = tspemail.getText().toString();
        String AADHARNUMBER = tspaadharnumber.getText().toString();
        String HOMETOWNADDRESS = tsphometownaddress.getText().toString();
        String USERNAME = tspusername.getText().toString();
        String PASSWORD = tsppassword.getText().toString();
        userID = fAuth.getCurrentUser().getUid();


        Tenant_helper_Class helperclass = new Tenant_helper_Class(userID, NAME, AGE, SEX, PHONENUMBER, EMAIL, AADHARNUMBER,
                HOMETOWNADDRESS, PROFFESSION(),COLLEGEORCOMPANYNAME(), COLLEGEORCOMPANYADDRESS(),  USERNAME, PASSWORD);
        fRef.child(userID).setValue(helperclass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    tspprogressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Tenant_Signup_Form.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    tspprogressbar.setVisibility(View.INVISIBLE);
                    String PHONENUMBER = tspphonenumber.getText().toString();
                    //Toast.makeText(getApplicationContext(), "Registartion Successful!", Toast.LENGTH_SHORT).show();
                    Intent into = new Intent(Tenant_Signup_Form.this, OTP_Page.class);
                    into.putExtra("PHONENUMBER", PHONENUMBER);
                    startActivity(into);
                }
            }
        });
    }

        private String COLLEGEORCOMPANYADDRESS() {
        if(tspstudent.isChecked()){
            return tspcollegeaddress.getText().toString();
        }
        else{
            return tspcompanyaddress.getText().toString();
        }
    }

        private String COLLEGEORCOMPANYNAME() {
        if(tspstudent.isChecked()){
            return tspcollegename.getText().toString();
        }
        else{
            return tspcompanyname.getText().toString();
        }

    }

        private String PROFFESSION() {
        if(tspstudent.isChecked()){
            return tspstudent.getText().toString();
        }
        else{
            return tspworking.getText().toString();
        }
    }
}