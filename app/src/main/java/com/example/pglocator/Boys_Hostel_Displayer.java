package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Boys_Hostel_Displayer extends AppCompatActivity {

    private RecyclerView bhdisplayerrecyclerview;
    ArrayList<addsite_helper_class> bhlist;
    Bh_RecyclerView_Adapter adapter;
    private DatabaseReference fData;
    private ProgressDialog postProgress;
    String bh, gh, bpg, gpg, rent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys__hostel__displayer);

        bhdisplayerrecyclerview =(RecyclerView) findViewById(R.id.bhdisplayerrecyclerView);
        bhdisplayerrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        bhlist = new ArrayList<addsite_helper_class>();
        postProgress = new ProgressDialog(this);
        postProgress.setMessage("Wait for few Moments.");


        DividerItemDecoration divideritemdecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        bhdisplayerrecyclerview.addItemDecoration(divideritemdecoration);


        bh = "Boys Hostel";
        gh = "Girls Hostel";
        bpg = "Boys PG";
        gpg = "Girls PG";
        rent = "Flat/House for Rent";

        Intent i = getIntent();
        String ID = i.getExtras().getString("buttonid");
        final String CITYID = i.getExtras().getString("cityid");


        if(ID.equals(bh)){
            fData = FirebaseDatabase.getInstance().getReference().child("Sites");
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){

                        addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);

                        String j = a.getType();
                        String z = a.getCity();

                        if(j.equals(bh) && z.equals(CITYID)){ bhlist.add(a); }
                    }
                    adapter = new Bh_RecyclerView_Adapter(Boys_Hostel_Displayer.this, bhlist);
                    bhdisplayerrecyclerview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
            postProgress.dismiss();
            return;
        }
        else if(ID.equals(gh)){
            fData = FirebaseDatabase.getInstance().getReference().child("Sites");
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){

                        addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);

                        String j = a.getType();
                        String z = a.getCity();

                        if(j.equals(gh) && z.equals(CITYID)){ bhlist.add(a); }
                    }
                    adapter = new Bh_RecyclerView_Adapter(Boys_Hostel_Displayer.this, bhlist);
                    bhdisplayerrecyclerview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
            postProgress.dismiss();
            return;
        }
        else if(ID.equals(bpg)){
            fData = FirebaseDatabase.getInstance().getReference().child("Sites");
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){

                        addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);

                        String j = a.getType();
                        String z = a.getCity();

                        if(j.equals(bpg) && z.equals(CITYID)){ bhlist.add(a); }
                    }
                    adapter = new Bh_RecyclerView_Adapter(Boys_Hostel_Displayer.this, bhlist);
                    bhdisplayerrecyclerview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
            postProgress.dismiss();
            return;
        }
        else if(ID.equals(gpg)){
            fData = FirebaseDatabase.getInstance().getReference().child("Sites");
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){

                        addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);

                        String j = a.getType();
                        String z = a.getCity();

                        if(j.equals(gpg) && z.equals(CITYID)){ bhlist.add(a); }
                    }
                    adapter = new Bh_RecyclerView_Adapter(Boys_Hostel_Displayer.this, bhlist);
                    bhdisplayerrecyclerview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
            postProgress.dismiss();
            return;
        }
        else {
            fData = FirebaseDatabase.getInstance().getReference().child("Sites");
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){

                        addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);

                        String j = a.getType();
                        String z = a.getCity();

                        if(j.equals(rent) && z.equals(CITYID)){ bhlist.add(a); }
                    }
                    adapter = new Bh_RecyclerView_Adapter(Boys_Hostel_Displayer.this, bhlist);
                    bhdisplayerrecyclerview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();

                }
            });
            postProgress.dismiss();
            return;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);

        MenuItem searchitem = menu.findItem(R.id.searchfilter);
        androidx.appcompat.widget.SearchView searchview = (androidx.appcompat.widget.SearchView) searchitem.getActionView();
        searchview.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}