package com.example.pglocator;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class homefragment extends Fragment {

    private ProgressDialog postProgress;
    private RecyclerView mBlogList;
    private DatabaseReference mpostDatabase;
    ArrayList<PostModelClass> blist;
    private PostAdapter postAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        postProgress = new ProgressDialog(getContext());
        postProgress.show();
        postProgress.setContentView(R.layout.progressbar);
        blist = new ArrayList<PostModelClass>();
        mBlogList = view.findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mBlogList.setLayoutManager(mLayoutManager);

        mpostDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mpostDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datasnapshot1 : dataSnapshot.getChildren()){
                    PostModelClass a = datasnapshot1.getValue(PostModelClass.class);
                    blist.add(a);
                }

                postAdapter = new PostAdapter(getActivity().getApplicationContext(), blist);
                mBlogList.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        postProgress.dismiss();
        return view;
         
    }
}
