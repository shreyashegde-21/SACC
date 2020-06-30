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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Owner_Signup_Form extends AppCompatActivity {
    private Button ospregisterbutton;
    private EditText ospfullname, ospage, ospphonenumber, ospemail,
            ospaadharnumber, ospusername, osppassword, ospconfirmpassword;
    private Spinner ospsexSpinner;
    private ProgressBar ospprogressbar;

    AwesomeValidation ospvalidation;

    private FirebaseDatabase fData;
    private DatabaseReference fRef;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__signup__form);
        fAuth = FirebaseAuth.getInstance();

        ospfullname = (EditText) findViewById(R.id.ospfullname);
        ospage = (EditText) findViewById(R.id.ospage);

        ospsexSpinner = (Spinner) findViewById(R.id.ospsexSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ospsexSpinner.setAdapter(adapter);

        ospphonenumber =(EditText) findViewById(R.id.ospphonenumber);
        ospemail =(EditText) findViewById(R.id.ospemail);
        ospaadharnumber =(EditText) findViewById(R.id.ospaadharnumber);
        ospusername =(EditText) findViewById(R.id.ospusername);
        osppassword =(EditText) findViewById(R.id.osppassword);
        ospconfirmpassword =(EditText) findViewById(R.id.ospconfirmpassword);
        ospregisterbutton = (Button) findViewById(R.id.ospregisterButton);
        ospprogressbar = (ProgressBar) findViewById(R.id.ospprogressbar);
    }

    private Boolean validatephonenumber(){
        String val = ospphonenumber.getText().toString();
        if(val.isEmpty()){
            ospphonenumber.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            ospphonenumber.setError(null);
            return true;
        }
    }

    private Boolean validateusername(){
        String val = ospusername.getText().toString();
        String nowhitespace = "\\A\\w{6,20}\\z";
        if(val.isEmpty()){
            ospusername.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(val.length()<6){
            ospusername.setError("Username Should Be Between 6-20 Characters.");
            return false;
        }
        else if(val.length()>20){
            ospusername.setError("Username Should Be Between 6-20 Characters.");
            return false;
        }
        else if(!val.matches(nowhitespace)){
            ospusername.setError("White Spaces Are Not Allowed.");
            return false;
        }
        else{
            ospusername.setError(null);
            return true;
        }
    }

    private Boolean validatepassword(){
        String val = osppassword.getText().toString();
        String passwordrequirements =   "^"+
                "(?=.*[a-zA-Z])"+
                "(?=.*[@#$%^&+=])"+
                ".{6,}"+
                "$";
        if(val.isEmpty()){
            osppassword.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else if(!val.matches(passwordrequirements)){
            osppassword.setError("password should contain 6-20 characters, atleast one any case alphabet" +
                    "and a special character without any spaces.");
            return false;
        }
        else{
            osppassword.setError(null);
            return true;
        }
    }

    public void Registeruser(View view){


        ospprogressbar.setVisibility(View.VISIBLE);

        ospvalidation = new AwesomeValidation(ValidationStyle.BASIC);
        ospvalidation.addValidation(this, R.id.ospfullname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        ospvalidation.addValidation(this, R.id.ospage, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        ospvalidation.addValidation(this, R.id.ospemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        ospvalidation.addValidation(this, R.id.ospaadharnumber, ".{12,}", R.string.invalid_aadhar);
        ospvalidation.addValidation(this, R.id.ospconfirmpassword, R.id.osppassword,R.string.invalid_confirmpassword);

        if(!ospvalidation.validate() | !validatephonenumber() | !validateusername() | !validatepassword()){

            ospprogressbar.setVisibility(View.INVISIBLE);
            return ;
        }

        fData = FirebaseDatabase.getInstance();
        fRef = fData.getReference("Owners");
        String NAME = ospfullname.getText().toString();
        int AGE = Integer.parseInt(ospage.getText().toString());
        String SEX = ospsexSpinner.getSelectedItem().toString();
        String PHONENUMBER = ospphonenumber.getText().toString();
        String EMAIL = ospemail.getText().toString();
        String AADHARNUMBER = ospaadharnumber.getText().toString();
        String USERNAME = ospusername.getText().toString();
        String PASSWORD = osppassword.getText().toString();

        Owner_helper_Class helperclass = new Owner_helper_Class(NAME, AGE, SEX, PHONENUMBER, EMAIL,
                AADHARNUMBER, USERNAME, PASSWORD);
        fRef.child(NAME).setValue(helperclass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(Owner_Signup_Form.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        fAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(Owner_Signup_Form.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        ospprogressbar.setVisibility(View.INVISIBLE);
        Intent into = new Intent(Owner_Signup_Form.this, OTP_Page.class);
        into.putExtra("PHONENUMBER",PHONENUMBER);
        startActivity(into);

    }
}