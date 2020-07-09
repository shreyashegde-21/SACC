package com.example.pglocator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {
    private ImageButton mSelectImage;
    private EditText mPostTitle, mPostDesc;
    private Button postSubmitBtn;

    private Uri postImageUri = null;
    private ProgressDialog postProgress;
    private StorageReference postStorage;
    private DatabaseReference postDatabase;

    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postStorage = FirebaseStorage.getInstance().getReference();
        postDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);
        mPostTitle = (EditText) findViewById(R.id.titleField);
        mPostDesc = (EditText) findViewById(R.id.descField);
        postSubmitBtn = (Button) findViewById(R.id.submitBtn);
        postProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        postSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {

        postProgress.setMessage("Posting Blog");

        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && postImageUri != null){
            postProgress.show();

            StorageReference filepath = postStorage.child("Blog_Images").child(postImageUri.getLastPathSegment());
            filepath.putFile(postImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imagelink = uri.toString();

                           /* PostModelClass a = new PostModelClass(title_val, desc_val, imagelink);
                            postDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()*/

                            DatabaseReference newPost = postDatabase.push();
                            newPost.child("title").setValue(title_val);
                            newPost.child("desc").setValue(desc_val);
                            newPost.child("image").setValue(imagelink);
                            newPost.child("uid").setValue(newPost.getKey());

                            postProgress.dismiss();

                            Intent i = new  Intent(getApplicationContext(), Home_Page.class);
                            startActivity(i);
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            postImageUri = data.getData();
            mSelectImage.setImageURI(postImageUri);
        }
    }





}