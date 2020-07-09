package com.example.pglocator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Liked_Displayer extends AppCompatActivity {

    private static final String TAG = "tp";
    private RecyclerView likeddisplayerrecyclerview;
    ArrayList<addsite_helper_class> likedfinallist;
    Liked_RecyclerView_Adapter adapter;
    private DatabaseReference fData, fsuperdata;
    private FirebaseAuth fAuth;
    private TextView partialtext, partialtext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked__displayer);
        fData = FirebaseDatabase.getInstance().getReference().child("Likes").child("Site_Likes");
        fAuth= FirebaseAuth.getInstance();
        partialtext = (TextView) findViewById(R.id.partialtext);
        partialtext.setVisibility(View.INVISIBLE);
        partialtext2 = (TextView) findViewById(R.id.partialtext2);
        partialtext2.setVisibility(View.INVISIBLE);

        likeddisplayerrecyclerview =(RecyclerView) findViewById(R.id.likeddisplayerrecyclerView);
        likeddisplayerrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divideritemdecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        likeddisplayerrecyclerview.addItemDecoration(divideritemdecoration);
        likedfinallist = new ArrayList<addsite_helper_class>();

    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
        fData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(fAuth.getCurrentUser().getUid())) {
                    fsuperdata = fData.child(fAuth.getCurrentUser().getUid());
                    fsuperdata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                                addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);
                                likedfinallist.add(a);
                            }

                            adapter = new Liked_RecyclerView_Adapter(Liked_Displayer.this, likedfinallist);
                            likeddisplayerrecyclerview.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {

                    likeddisplayerrecyclerview.setVisibility(View.GONE);
                    partialtext.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }else{
        likeddisplayerrecyclerview.setVisibility(View.GONE);
        partialtext2.setVisibility(View.VISIBLE);
    }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        likedfinallist.clear();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(fAuth.getCurrentUser().getUid())) {
                        fsuperdata = fData.child(fAuth.getCurrentUser().getUid());
                        fsuperdata.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                                    addsite_helper_class a = datasnapshot1.getValue(addsite_helper_class.class);
                                    likedfinallist.add(a);
                                }

                                adapter = new Liked_RecyclerView_Adapter(Liked_Displayer.this, likedfinallist);
                                likeddisplayerrecyclerview.setAdapter(adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {

                        likeddisplayerrecyclerview.setVisibility(View.GONE);
                        partialtext.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            likeddisplayerrecyclerview.setVisibility(View.GONE);
            partialtext2.setVisibility(View.VISIBLE);
        }


    }
}