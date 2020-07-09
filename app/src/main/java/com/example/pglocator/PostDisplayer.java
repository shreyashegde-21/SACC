package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class PostDisplayer extends AppCompatActivity {

    private TextView postedTitle;
    private TextView postedDesc;
    private ImageView postedImage;
    Button postlikebutton;
    private Boolean mProcessLike = false;
    private DatabaseReference mdatabaselike, mdatabaseblog;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_displayer);
        mdatabaselike = FirebaseDatabase.getInstance().getReference().child("Likes").child("Blog_Likes");
        mdatabaseblog = FirebaseDatabase.getInstance().getReference().child("Blog");
        fAuth = FirebaseAuth.getInstance();

        postedTitle = (TextView) findViewById(R.id.titlePosted);
        postedDesc = (TextView) findViewById(R.id.descPosted);
        postedImage = (ImageView) findViewById(R.id.postedImage);
        postlikebutton = (Button) findViewById(R.id.postlikebutton);




        Intent i = getIntent();
        String BLOGTITLE = i.getExtras().getString("blogtitle");
        String BLOGDESCRIPTION = i.getExtras().getString("blogdes");
        String BLOGIMAGE = i.getExtras().getString("blogimg");
        final String BLOGUID = i.getExtras().getString("bloguid");



        Picasso.get().load(BLOGIMAGE).into(postedImage);
        postedTitle.setText(BLOGTITLE);
        postedDesc.setText(BLOGDESCRIPTION);
        setLikeButton(BLOGUID);


        postlikebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mProcessLike = true;
                    mdatabaselike.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (mProcessLike) {
                                if (dataSnapshot.child(fAuth.getCurrentUser().getUid()).hasChild(BLOGUID)) {
                                    mdatabaselike.child(fAuth.getCurrentUser().getUid()).child(BLOGUID).removeValue();
                                    mProcessLike = false;

                                } else {
                                    mdatabaseblog.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                                                PostModelClass a = datasnapshot1.getValue(PostModelClass.class);
                                                if (a.getUID().equals(BLOGUID)) {
                                                    mdatabaselike.child(fAuth.getCurrentUser().getUid()).child(BLOGUID).setValue(a);
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
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            mdatabaselike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(fAuth.getCurrentUser().getUid()).hasChild(a)) {
                        postlikebutton.setBackgroundResource(R.drawable.favred);
                    } else {
                        postlikebutton.setBackgroundResource(R.drawable.favgrey);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }else{
            postlikebutton.setBackgroundResource(R.drawable.favgrey);
        }
    }
}