package com.example.pglocator;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class Login_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView loginpagesignuppagetaker,loginpageverifyemaillink,
            loginpagehomepagetaker, loginpageforgotpasswordinitializer;
    private EditText loginpageemail, loginpagePassword;
    private Button loginpageloginbutton;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;

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
                PopupMenu ownerortenant = new PopupMenu(getApplicationContext(), view);
                ownerortenant.setOnMenuItemClickListener(Login_Activity.this);
                ownerortenant.inflate(R.menu.popup_menu_tenant_or_owner);
                ownerortenant.show();
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
                                Toast.makeText(Login_Activity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.tenant:
                loginpagesignuppagetaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent into = new Intent(Login_Activity.this, Tenant_Signup_Form.class);
                        startActivity(into);
                    }
                });
                return true;

            case R.id.owner:
                loginpagesignuppagetaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent into1 = new Intent(Login_Activity.this, Owner_Signup_Form.class);
                        startActivity(into1);
                    }
                });
                return true;
            default:
                return false;
        }
    }
}