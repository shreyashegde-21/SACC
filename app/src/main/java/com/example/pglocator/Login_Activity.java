package com.example.pglocator;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity{
    private TextView loginpagesignuppagetaker,loginpageverifyemaillink,
            loginpagehomepagetaker, loginpageforgotpasswordinitializer;
    private EditText loginpageemail, loginpagePassword;
    private Button loginpageloginbutton;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;
    private DatabaseReference fData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        fAuth = FirebaseAuth.getInstance();
        loginpageemail = (EditText) findViewById(R.id.loginpageemail);
        loginpagesignuppagetaker = findViewById(R.id.loginpageregisterationlink);
        loginpageverifyemaillink = (TextView) findViewById(R.id.loginpageverifyemaillink);
        loginpagePassword = (EditText) findViewById(R.id.loginpagepassword);
        loginpageforgotpasswordinitializer = findViewById(R.id.loginpageforgotpaswordlink);
        loginpageloginbutton = (Button) findViewById(R.id.loginpageloginbutton);
        loginpagehomepagetaker = findViewById(R.id.loginpagehomepagelink);


        loginpageverifyemaillink.setVisibility(View.INVISIBLE);
        loginpageverifyemaillink.setClickable(false);

        if((fAuth.getCurrentUser() != null) && (checkEmailVerification())){
            startActivity(new Intent(Login_Activity.this, Home_Page.class));
            finish();
        }

        loginpagesignuppagetaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ownerortenantselection.class));
            }
        });

        loginpageverifyemaillink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fUser = fAuth.getCurrentUser();
                if(fUser!=null){
                    fUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Verification Mail Sent.\nPlease verify your mail to login.", Toast.LENGTH_LONG).show();
                                fAuth.signOut();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Verification Mail couldn't be sent.\nTry Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        loginpageforgotpasswordinitializer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent into = new Intent(Login_Activity.this, Forgot_Password.class);
                startActivity(into);
            }
        });

        loginpagehomepagetaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Login_Activity.this, Home_Page.class);
                startActivity(int1);
            }
        });

        loginpageloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String E = loginpageemail.getText().toString();
                String P = loginpagePassword.getText().toString();

                if((E.isEmpty() && P.isEmpty())){
                    Toast.makeText(Login_Activity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(E.isEmpty()){
                    loginpageemail.setError("Please enter E-Mail.");
                    loginpageemail.requestFocus();
                    return;
                }
                if(P.isEmpty()){
                    loginpagePassword.setError("Please enter Password.");
                    loginpagePassword.requestFocus();
                    return;
                }
                fAuth.signInWithEmailAndPassword(E, P).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            if(checkEmailVerification()){
                                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login_Activity.this, Home_Page.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                            else{
                                loginpageverifyemaillink.setVisibility(View.VISIBLE);
                                loginpageverifyemaillink.setClickable(true);
                                loginpageverifyemaillink.setError("PLease Verify your Email");
                                loginpageverifyemaillink.requestFocus();
                            }

                        }
                    }
                });
            }
        });

        checkconnection();
    }

    private void checkconnection() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork == null){
        Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkEmailVerification() {
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailverified = fUser.isEmailVerified();

        if(emailverified){
            return true;
        }
        else{
            return false;
        }
    }
}