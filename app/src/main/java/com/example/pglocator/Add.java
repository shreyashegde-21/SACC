package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {
    private EditText afname, afaddress, afpincode, afmobileno, afemail, afrentpermoth;
    private Spinner afcityspinner, aftypespinner, afroomtypespinner;
    private CheckBox afcbmarket, afcbclinicorhospital, afcbmall, afcbtheatre,
            afcbeateries, afcbgroundorpark, afcbstationary, afcbgym;
    private ArrayList<String> afcheckboxresults;
    private TextView affacilityinvicinitytv, affoodavail, affurnish, afacavail;
    private RadioGroup afrgfoodavail, afrgfurnish, afrgacavail;
    private RadioButton affoodavailyes, affoodavailno, affurnishyes, affurnishsomewhat,
            affurnishno, afacavailyes, afacavailno;
    private ImageView afimageview;
    public static final int IMAGECODE =1;
    Uri imageuri;
    private Button afaddsitebutton;
    private ProgressBar afprogressbar;

    AwesomeValidation afvalidation;

    private FirebaseDatabase fData;
    private DatabaseReference fRef;
    private FirebaseAuth fAuth;
    private StorageReference postStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        postStorage = FirebaseStorage.getInstance().getReference();

        afname = (EditText) findViewById(R.id.afname);
        afaddress = (EditText) findViewById(R.id.afaddress);

        afcityspinner =(Spinner) findViewById(R.id.afcityspinner);
        List<String> cities = new ArrayList<String>();
        cities.add(0, "Select your city");
        cities.add("Bangalore");
        cities.add("Chennai");
        cities.add("Delhi");
        cities.add("Kolkata");
        cities.add("Mumbai");
        ArrayAdapter<String> cityarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        cityarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        afcityspinner.setAdapter(cityarrayadapter);

        afpincode = (EditText) findViewById(R.id.afpincode);
        afmobileno = (EditText) findViewById(R.id.afphonenumber);
        afemail = (EditText) findViewById(R.id.afemail);

        aftypespinner =(Spinner) findViewById(R.id.aftypespinner);
        List<String> type = new ArrayList<String>();
        type.add("Select type of facility");
        type.add("Boys Hostel");
        type.add("Girls Hostel");
        type.add("Boys PG");
        type.add("Girls PG");
        type.add("Flat/House for Rent");
        ArrayAdapter<String> typearrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        typearrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aftypespinner.setAdapter(typearrayadapter);
        aftypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aftypespinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        afrentpermoth = (EditText) findViewById(R.id.afrentpermonth);

        affacilityinvicinitytv = (TextView) findViewById(R.id.affacilityinvicinitytv);
       afcbmarket = findViewById(R.id.afcbmarket);
       afcbclinicorhospital = findViewById(R.id.afcbhospitalorclinic);
       afcbmall = findViewById(R.id.afcbmall);
       afcbtheatre = findViewById(R.id.afcbtheatre);
       afcbeateries = findViewById(R.id.afcbeateries);
       afcbgroundorpark = findViewById(R.id.afcbgroundpark);
       afcbstationary = findViewById(R.id.afcbstationary);
       afcbgym = findViewById(R.id.afcbgym);
       afcheckboxresults = new ArrayList<>();
       afcbmarket.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (afcbmarket.isChecked()){
                   afcheckboxresults.add("Market");
               }
               else{
                   afcheckboxresults.remove("Market");
               }
           }
       });
        afcbclinicorhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbclinicorhospital.isChecked()){
                    afcheckboxresults.add("Hospital/Clinic");
                }
                else{
                    afcheckboxresults.remove("Hospital/Clinic");
                }
            }
        });
        afcbmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbmall.isChecked()){
                    afcheckboxresults.add("Mall");
                }
                else{
                    afcheckboxresults.remove("Mall");
                }
            }
        });
        afcbtheatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbtheatre.isChecked()){
                    afcheckboxresults.add("Theatre");
                }
                else{
                    afcheckboxresults.remove("Theatre");
                }
            }
        });
        afcbeateries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbeateries.isChecked()){
                    afcheckboxresults.add("Eateries");
                }
                else{
                    afcheckboxresults.remove("Eateries");
                }
            }
        });
        afcbgroundorpark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbgroundorpark.isChecked()){
                    afcheckboxresults.add("Ground/Park");
                }
                else{
                    afcheckboxresults.remove("Ground/Park");
                }
            }
        });
        afcbstationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbstationary.isChecked()){
                    afcheckboxresults.add("Stationary");
                }
                else{
                    afcheckboxresults.remove("Stationary");
                }
            }
        });
        afcbgym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (afcbgym.isChecked()){
                    afcheckboxresults.add("Gym");
                }
                else{
                    afcheckboxresults.remove("Gym");
                }
            }
        });



        affoodavail = (TextView) findViewById(R.id.affoodavaibilitytextview);
        afrgfoodavail = (RadioGroup) findViewById(R.id.affoodavaibilityradiogroup);
        affoodavailyes = (RadioButton) findViewById(R.id.affarbyes);
        affoodavailno = (RadioButton) findViewById(R.id.affarbno);

        affurnish = (TextView) findViewById(R.id.affurnishmenttextview);
        afrgfurnish = (RadioGroup) findViewById(R.id.affurnishmentradiogroup);
        affurnishyes = (RadioButton) findViewById(R.id.affrbyes);
        affurnishno = (RadioButton) findViewById(R.id.affrbno);
        affurnishsomewhat = (RadioButton) findViewById(R.id.affrbsomewhat);

        afacavail = (TextView) findViewById(R.id.afacavailibiliytextview);
        afrgacavail = (RadioGroup) findViewById(R.id.afacavalibilityradiogroup);
        afacavailyes = (RadioButton) findViewById(R.id.afacarbyes);
        afacavailno = (RadioButton) findViewById(R.id.afacarbno);

        afroomtypespinner =(Spinner) findViewById(R.id.afroomtypespinner);
        List<String> roomtype = new ArrayList<String>();
        roomtype.add("Select type of Room");
        roomtype.add("1 RK");
        roomtype.add("1 BHK");
        roomtype.add("2 BHK");
        ArrayAdapter<String> roomtypearrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomtype);
        roomtypearrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        afroomtypespinner.setAdapter(roomtypearrayadapter);
        afroomtypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                afroomtypespinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        afimageview = (ImageView) findViewById(R.id.afimageview);
        afimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openimage();
            }
        });

        afaddsitebutton = (Button) findViewById(R.id.afaddsiteButton);
        afprogressbar = (ProgressBar) findViewById(R.id.afasprogressbar);
    }

    private void openimage() {
        Intent gallery  = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery, IMAGECODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGECODE && resultCode==RESULT_OK && data != null && data.getData()!=null){

            imageuri = data.getData();
            afimageview.setImageURI(imageuri);
        }
    }

    private Boolean validateafcityspinner() {

        String city = afcityspinner.getSelectedItem().toString();
        String val = "Select your city";
        if(city == val){
            Toast.makeText(getApplicationContext(), "Field 'City' needs to be selected!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean validateafphonenumber(){
        String val = afmobileno.getText().toString();
        if(val.isEmpty()){
            afmobileno.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            afmobileno.setError(null);
            return true;
        }
    }

    private Boolean validatetypespinner() {

        String type = aftypespinner.getSelectedItem().toString();
        String val = "Select type of facility";
        if(type == val){
            Toast.makeText(getApplicationContext(), "Field 'Type' needs to be selected!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean validatecheckbox() {
    if(!afcbmarket.isChecked() && !afcbclinicorhospital.isChecked() && !afcbmall.isChecked() && !afcbtheatre.isChecked()
            && !afcbeateries.isChecked() && !afcbgroundorpark.isChecked() && !afcbstationary.isChecked() && !afcbgym.isChecked()){
       affacilityinvicinitytv.setError("Select atleast one field");
       return false;
    }
    else{
        return true;
    }
    }

    private Boolean validatefoodavailradiogroup(){
        int val = afrgfoodavail.getCheckedRadioButtonId();
        if(val == -1){
            affoodavail.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            affoodavail.setError(null);
            return true;
        }
    }

    private Boolean validatefurnishradiogroup(){
        int val = afrgfurnish.getCheckedRadioButtonId();
        if(val == -1){
            affurnish.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            affurnish.setError(null);
            return true;
        }
    }

    private Boolean validateacavailradiogroup(){
        int val = afrgacavail.getCheckedRadioButtonId();
        if(val == -1){
            afacavail.setError("This Field Cannot Be Left Empty.");
            return false;
        }
        else{
            afacavail.setError(null);
            return true;
        }
    }

    private Boolean validateroomtypespinner() {

        String roomtype = afroomtypespinner.getSelectedItem().toString();
        String val = "Select type of Room";
        if(roomtype == val){
            Toast.makeText(getApplicationContext(), "Field 'Room Type' needs to be selected!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean validatephoto() {
        if(imageuri != null){
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(), "Atleast one site pic needs to be uploaded!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void validatesite(View view) {
        afprogressbar.setVisibility(View.VISIBLE);

        afvalidation = new AwesomeValidation(ValidationStyle.BASIC);
        afvalidation.addValidation(this, R.id.afname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        afvalidation.addValidation(this, R.id.afaddress, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        afvalidation.addValidation(this, R.id.afpincode, RegexTemplate.NOT_EMPTY, R.string.invalid_age);
        afvalidation.addValidation(this, R.id.afemail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        afvalidation.addValidation(this, R.id.afrentpermonth, RegexTemplate.NOT_EMPTY, R.string.invalid_age);


        if(!afvalidation.validate() | !validatephoto() | !validateroomtypespinner() | !validateafphonenumber() | !validatetypespinner() | !validatecheckbox()
                | !validatefoodavailradiogroup() | !validatefurnishradiogroup() | !validateacavailradiogroup() |  !validateafcityspinner()){
            afprogressbar.setVisibility(View.INVISIBLE);
            return ;
        }
        storepic();
    }

    private void storepic() {
        StorageReference filepath = postStorage.child("Site_Images").child(imageuri.getLastPathSegment());
        filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        fData = FirebaseDatabase.getInstance();
                        fRef = fData.getReference("Sites");
                        fAuth = FirebaseAuth.getInstance();
                        String imagelink = uri.toString();
                        String AFNAME = afname.getText().toString();
                        String AFADDRESS = afaddress.getText().toString();
                        String AFCITY = afcityspinner.getSelectedItem().toString();
                        String AFPINCODE = afpincode.getText().toString();
                        String AFPHONENUMBER = afmobileno.getText().toString();
                        String AFEMAIL = afemail.getText().toString();
                        String AFTYPE = aftypespinner.getSelectedItem().toString();
                        String AFRENTPERMONTH = afrentpermoth.getText().toString();
                        String AFROOMTYPE = afroomtypespinner.getSelectedItem().toString();

                        addsite_helper_class afhelperclass = new addsite_helper_class(imagelink, AFNAME, AFADDRESS, AFCITY, AFPINCODE, AFPHONENUMBER, AFEMAIL,
                                AFTYPE, AFRENTPERMONTH, FACILITYINVICINITY(), FOODAVAIL(), FURNISHMENT(), ACAVAIL(), AFROOMTYPE);
                        fRef.child(AFPHONENUMBER).setValue(afhelperclass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()){
                                    afprogressbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else{
                                    afprogressbar.setVisibility(View.INVISIBLE);
                                    //String PHONENUMBER = tspphonenumber.getText().toString();
                                    Toast.makeText(getApplicationContext(), "Addition Of Site Successful!!", Toast.LENGTH_SHORT).show();
                                    Intent into = new Intent(Add.this, Home_Page.class);
                                    into.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    //into.putExtra("PHONENUMBER", PHONENUMBER);
                                    startActivity(into);
                                }
                            }
                        });

                    }
                });
            }
        });
    }

    private void sendtodatabase() {



    }

    private ArrayList<String> FACILITYINVICINITY() {
        return  afcheckboxresults;
    }

    private String FOODAVAIL() {
        if(affoodavailyes.isChecked()){
            return affoodavailyes.getText().toString();
        }
        else{
            return affoodavailno.getText().toString();
        }
    }

    private String FURNISHMENT() {
        if(affurnishyes.isChecked()){
            return affurnishyes.getText().toString();
        }
        else if(affurnishsomewhat.isChecked()){
            return affurnishsomewhat.getText().toString();
        }
        else{
            return affurnishyes.getText().toString();
        }
    }

    private String ACAVAIL() {
        if(afacavailyes.isChecked()){
            return afacavailyes.getText().toString();
        }
        else{
            return afacavailno.getText().toString();
        }
    }
}