package com.example.pglocator;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profilefragment extends Fragment {

    private ImageView profileimage;
    private TextView profilename, profileusername, profilemobileno, profileemail, profileage,
            profilesex, profileproffession, profileproffname, profileproffaddress, profilehometownaddress;
    FirebaseAuth fAuth;
    FirebaseDatabase fData;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        fAuth = FirebaseAuth.getInstance();
        fData = FirebaseDatabase.getInstance();

        profileimage = (ImageView) view.findViewById(R.id.profileimage);
        profilename = (TextView) view.findViewById(R.id.profilename);
        profileusername = (TextView) view.findViewById(R.id.profileusername);
        profilemobileno= (TextView) view.findViewById(R.id.profilemobileno);
        profileemail = (TextView) view.findViewById(R.id.profileemail);
        profileage = (TextView) view.findViewById(R.id.profileage);
        profilesex = (TextView) view.findViewById(R.id.profilesex);
        profileproffession = (TextView) view.findViewById(R.id.profileproffession);
        profileproffname = (TextView) view.findViewById(R.id.profileproffessionname);
        profileproffaddress = (TextView) view.findViewById(R.id.profileproffessionaddress);
        profilehometownaddress = (TextView) view.findViewById(R.id.profilehometownaddress);

        DatabaseReference dRef = fData.getReference(String.valueOf(fAuth.getCurrentUser()));

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Tenant_helper_Class helper = dataSnapshot.getValue(Tenant_helper_Class.class);
              profilename.setText(helper.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });












        return view;
    }
}
