package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LikedBlogs extends AppCompatActivity {

    private RecyclerView likedblogdisplayerrecyclerview;
    ArrayList<PostModelClass> likedblogfinallist;
    LikedBlogs_Recyclerviewadapter adapter;
    private TextView partialtext1, partialtext3;
    private DatabaseReference fData, fsuperdata;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_blogs);

        fData = FirebaseDatabase.getInstance().getReference().child("Likes").child("Blog_Likes");
        fAuth= FirebaseAuth.getInstance();
        partialtext1 = (TextView) findViewById(R.id.partialtext1);
        partialtext1.setVisibility(View.INVISIBLE);
        partialtext3 = (TextView) findViewById(R.id.partialtext3);
        partialtext3.setVisibility(View.INVISIBLE);

        likedblogdisplayerrecyclerview =(RecyclerView) findViewById(R.id.likedblogdisplayerrecyclerView);
        likedblogdisplayerrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        likedblogfinallist = new ArrayList<PostModelClass>();


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
                                    PostModelClass a = datasnapshot1.getValue(PostModelClass.class);
                                    likedblogfinallist.add(a);
                                }

                                adapter = new LikedBlogs_Recyclerviewadapter(getApplicationContext(), likedblogfinallist);
                                likedblogdisplayerrecyclerview.setAdapter(adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } else {

                        likedblogdisplayerrecyclerview.setVisibility(View.GONE);
                        partialtext1.setVisibility(View.VISIBLE);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{

            likedblogdisplayerrecyclerview.setVisibility(View.GONE);
            partialtext3.setVisibility(View.VISIBLE);
        }




    }
}