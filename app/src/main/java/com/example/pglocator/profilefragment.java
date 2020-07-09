package com.example.pglocator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profilefragment extends Fragment {

    private ProgressDialog postProgress;
    private ImageView profileimage;
    private TextView profilename, profileusername, profilemobileno, profileemail, profileage,
            profilesex, profileproffession, profileproffname, profileproffaddress, profilehometownaddress;
    private  LinearLayout partiallayout;
    private FirebaseAuth fAuth;
    private DatabaseReference fDatatenant;
    private DatabaseReference fDataowner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        postProgress = new ProgressDialog(getContext());
        postProgress.show();
        postProgress.setContentView(R.layout.progressbar);

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        fAuth = FirebaseAuth.getInstance();

        profileimage = (ImageView) view.findViewById(R.id.profileimage);
        profilename = (TextView) view.findViewById(R.id.profilename);
        profileusername = (TextView) view.findViewById(R.id.profileusername);
        profilemobileno = (TextView) view.findViewById(R.id.profilemobileno);
        profileemail = (TextView) view.findViewById(R.id.profileemail);
        profileage = (TextView) view.findViewById(R.id.profileage);
        profilesex = (TextView) view.findViewById(R.id.profilesex);
        profileproffession = (TextView) view.findViewById(R.id.profileproffession);
        profileproffname = (TextView) view.findViewById(R.id.profileproffessionname);
        profileproffaddress = (TextView) view.findViewById(R.id.profileproffessionaddress);
        profilehometownaddress = (TextView) view.findViewById(R.id.profilehometownaddress);
        partiallayout = (LinearLayout) view.findViewById(R.id.partiallayout);
        partiallayout.setVisibility(View.INVISIBLE);

        if(fAuth.getCurrentUser() != null){
            setData();
            postProgress.dismiss();
        }
        else{
            postProgress.dismiss();
        }

        return view;
    }

    private void setData() {
        final String userID = fAuth.getCurrentUser().getUid();

        fDatatenant = FirebaseDatabase.getInstance().getReference("Users");
        fDataowner = FirebaseDatabase.getInstance().getReference("Owners");
        Query checkuser = fDatatenant.orderByChild("uid").equalTo(userID);
        Query checkowner = fDataowner.orderByChild("uid").equalTo(userID);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    partiallayout.setVisibility(View.VISIBLE);

                    String emailfromdb = dataSnapshot.child(userID).child("email").getValue(String.class);
                    String namefromdb = dataSnapshot.child(userID).child("name").getValue(String.class);
                    int agefromdb = dataSnapshot.child(userID).child("age").getValue(int.class);
                    String sexfromdb = dataSnapshot.child(userID).child("sex").getValue(String.class);
                    String phonenumberfromdb = dataSnapshot.child(userID).child("phonenumber").getValue(String.class);
                    String aadharfromdb = dataSnapshot.child(userID).child("aadharnumber").getValue(String.class);
                    String hometownfromdb = dataSnapshot.child(userID).child("hometownaddress").getValue(String.class);
                    String proffessionfromdb = dataSnapshot.child(userID).child("proffession").getValue(String.class);
                    String proffnamefromdb = dataSnapshot.child(userID).child("college_Or_Company_Name").getValue(String.class);
                    String proffaddfromdb = dataSnapshot.child(userID).child("college_Or_Company_Address").getValue(String.class);
                    String usernamefromdb = dataSnapshot.child(userID).child("username").getValue(String.class);

                    profilename.setText(namefromdb);
                    profileage.setText(String.valueOf(agefromdb));
                    profilesex.setText(sexfromdb);
                    profileproffession.setText(proffessionfromdb);
                    profileusername.setText(usernamefromdb);
                    profilemobileno.setText(phonenumberfromdb);
                    profileemail.setText(emailfromdb);
                    profileproffname.setText(proffnamefromdb);
                    profileproffaddress.setText(proffaddfromdb);
                    profilehometownaddress.setText(hometownfromdb);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        checkowner.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    String emailfromdb = dataSnapshot.child(userID).child("email").getValue(String.class);
                    String namefromdb = dataSnapshot.child(userID).child("name").getValue(String.class);
                    int agefromdb = dataSnapshot.child(userID).child("age").getValue(int.class);
                    String sexfromdb = dataSnapshot.child(userID).child("sex").getValue(String.class);
                    String phonenumberfromdb = dataSnapshot.child(userID).child("phonenumber").getValue(String.class);
                    String aadharfromdb = dataSnapshot.child(userID).child("aadharnumber").getValue(String.class);
                    String usernamefromdb = dataSnapshot.child(userID).child("username").getValue(String.class);

                    profilename.setText(namefromdb);
                    profileage.setText(String.valueOf(agefromdb));
                    profilesex.setText(sexfromdb);
                    profileusername.setText(usernamefromdb);
                    profilemobileno.setText(phonenumberfromdb);
                    profileemail.setText(emailfromdb);
                    profileproffession.setText("Working");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
