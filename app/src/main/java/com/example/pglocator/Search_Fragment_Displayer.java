package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Search_Fragment_Displayer extends AppCompatActivity {

    private TextView sfname, sfaddress, sfcity, sfpincode, sfphonenumber, sfemail, sftype, sfrentpermonth,
            sffacilityinvicinity, sffoodavailability, affurnishment, afacavailability, sfroomtype;
    private ImageView imagee;
    private Button sfdlikebutton;
    private DatabaseReference mdatabaselike, mdatabasesite;
    private FirebaseAuth fAuth;
    private Boolean mProcessLike = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__fragment__displayer);
        fAuth = FirebaseAuth.getInstance();
        mdatabaselike = FirebaseDatabase.getInstance().getReference().child("Likes").child("Site_Likes");
        mdatabasesite = FirebaseDatabase.getInstance().getReference().child("Sites");
        mdatabaselike.keepSynced(true);

        Intent intent = getIntent();
        String image = intent.getExtras().getString("bhimage");
        String name = intent.getExtras().getString("bhname");
        String address = intent.getExtras().getString("bhaddress");
        String city = intent.getExtras().getString("bhcity");
        String pincode = intent.getExtras().getString("bhpincode");
        final String phonenumber = intent.getExtras().getString("bhphonenumber");
        String email = intent.getExtras().getString("bhemail");
        String type = intent.getExtras().getString("bhtype");
        String rentpermonth = intent.getExtras().getString("bhrentpermonth");
        ArrayList<String> facilityinvicinity = intent.getExtras().getStringArrayList("bhfacilitieesinvicinty");
        String foodavailability = intent.getExtras().getString("bhfoodavail");
        String furnishment = intent.getExtras().getString("bhfurnish");
        String acavailability = intent.getExtras().getString("bhacavail");
        String roomtype = intent.getExtras().getString("bhroomtype");

        sfname = (TextView) findViewById(R.id.sfdname);
        sfaddress = (TextView) findViewById(R.id.sfdaddress);
        sfcity = (TextView) findViewById(R.id.sfdcity);
        sfpincode = (TextView) findViewById(R.id.sfdpincode);
        sfphonenumber = (TextView) findViewById(R.id.sfdphonenumber);
        sfemail = (TextView) findViewById(R.id.sfdemail);
        sftype = (TextView) findViewById(R.id.sfdtype);
        sfrentpermonth = (TextView) findViewById(R.id.sfdrentpermonth);
        sffacilityinvicinity = (TextView) findViewById(R.id.sfdfacilitiesinvicinity);
        sffoodavailability = (TextView) findViewById(R.id.sfdfoodavail);
        affurnishment = (TextView) findViewById(R.id.sfdfurnishment);
        afacavailability = (TextView) findViewById(R.id.sfdacavail);
        sfroomtype = (TextView) findViewById(R.id.sfdroomtype);
        imagee = (ImageView) findViewById(R.id.imageView2);
        sfdlikebutton = (Button) findViewById(R.id.sfdlikebutton);
        setLikeButton(phonenumber);

        String Facilities_In_Vicinity = "";
        for( String Facilities : facilityinvicinity){
                Facilities_In_Vicinity = Facilities_In_Vicinity+Facilities+"\n";
            }

        Picasso.get().load(image).into(imagee);
        sfname.setText(name);
        sfaddress.setText(address);
        sfcity.setText(city);
        sfpincode.setText(pincode);
        sfphonenumber.setText(phonenumber);
        sfemail.setText(email);
        sftype.setText(type);
        sfrentpermonth.setText(rentpermonth);
        sffacilityinvicinity.setText(Facilities_In_Vicinity);
        sffoodavailability.setText(foodavailability);
        affurnishment.setText(furnishment);
        afacavailability.setText(acavailability);
        sfroomtype.setText(roomtype);

        sfdlikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mProcessLike = true;
                    mdatabaselike.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (mProcessLike) {
                                if (dataSnapshot.child(fAuth.getCurrentUser().getUid()).hasChild(phonenumber)) {
                                    mdatabaselike.child(fAuth.getCurrentUser().getUid()).child(phonenumber).removeValue();
                                    mProcessLike = false;

                                } else {
                                    mdatabasesite.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                                                addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);
                                                if (a.getPhoneNumber().equals(phonenumber)) {
                                                    mdatabaselike.child(fAuth.getCurrentUser().getUid()).child(phonenumber).setValue(a);
                                                    mProcessLike = false;
                                                }

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Please Login First!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setLikeButton(final String a){
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            mdatabaselike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(fAuth.getCurrentUser().getUid()).hasChild(a)) {
                        sfdlikebutton.setBackgroundResource(R.drawable.favred);
                    } else {
                        sfdlikebutton.setBackgroundResource(R.drawable.favgrey);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }else{
            sfdlikebutton.setBackgroundResource(R.drawable.favgrey);
        }
    }
}