package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    private EditText emaildetails;
    private Button selectiontaker;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        emaildetails = (EditText) findViewById(R.id.fpemailrecognizer);
        selectiontaker = (Button) findViewById(R.id.fpresetpasswordbutton);
        fauth = FirebaseAuth.getInstance();

        selectiontaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = emaildetails.getText().toString();
                fauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Forgot_Password.this, "Reset Password Mail sent", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Forgot_Password.this, Login_Activity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Forgot_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}