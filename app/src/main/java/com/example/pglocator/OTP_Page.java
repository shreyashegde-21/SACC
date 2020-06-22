package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class OTP_Page extends AppCompatActivity {

    private TextView phonenodisplayer;
    private Button verifycode;
    private ProgressBar progressbar;
    private String verificationcodebysystem;
    private PinView codeenteredinpinview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__page);

        phonenodisplayer = (TextView) findViewById(R.id.phonenodisplayer);
        codeenteredinpinview = (PinView) findViewById(R.id.pinview);
        verifycode = (Button) findViewById(R.id.verifycode);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        Intent intent = getIntent();
        String Phonenumber = getIntent().getStringExtra("PHONENUMBER");

        phonenodisplayer.setText(Phonenumber);
        sendVerificationCodeToUser(Phonenumber);

    }

    private void sendVerificationCodeToUser(String phonenumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,                        // Phone number to verify
                60,                                      // Timeout duration
                TimeUnit.SECONDS,                        // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);                             // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks= new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationcodebysystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null) {
                codeenteredinpinview.setText(code);
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTP_Page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifycode(String codebyuser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcodebysystem, codebyuser);
        signInWithPhoneAuthCredential(credential);
    }

    /*private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseauth = FirebaseAuth.getInstance();
        firebaseauth.signInWithCredential(credential).addOnCompleteListener(OTP_Page.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Intent into = new Intent(getApplicationContext(), Home_Page.class);
                   into.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(into);
               }
               else{
                   Toast.makeText(OTP_Page.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
               }
            }
        });
    }*/

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseauth = FirebaseAuth.getInstance();

        firebaseauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OTP_Page.this, "completed", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OTP_Page.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void FinalVerification(View view) {
        String cd = codeenteredinpinview.getText().toString();
        if(!cd.isEmpty()){
            verifycode(cd);
        }

        Intent into = new Intent(OTP_Page.this, Home_Page.class);
        startActivity(into);
    }
}