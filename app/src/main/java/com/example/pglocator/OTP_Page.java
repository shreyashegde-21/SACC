package com.example.pglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class OTP_Page extends AppCompatActivity {

    private TextView otppphonenodisplayer, otpptimerdisplayer, otppresendotp;
    private PinView otppcodeenteredinpinview;
    private Button otppverifycodebutton;
    private ProgressBar otppprogressbar;
    private String verificationcodebysystem;
    private CountDownTimer mCountDownTimer;
    private Boolean mTimerRunning;
    private static final long START_TIME_IN_MILIS = 60000;
    private long mTimeLeftInMillis = START_TIME_IN_MILIS;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;

    /*private TextView tspproffessiontextview;
    private RadioGroup tspradiogroup;
    private RadioButton tspstudent, tspworking;
    private EditText tspfullname, tspage, tspphonenumber, tspemail,
            tspaadharnumber, tsphometownaddress, tspusername, tsppassword, tspconfirmpassword,
            tspcollegename, tspcollegeaddress, tspcompanyname, tspcompanyaddress;
    private Spinner tspsexSpinner;
    private FirebaseDatabase fData;
    private DatabaseReference fRef;
    private FirebaseAuth fAuth;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__page);
        fAuth = FirebaseAuth.getInstance();

        otppphonenodisplayer = (TextView) findViewById(R.id.otppphonenodisplayer);
        otppcodeenteredinpinview = (PinView) findViewById(R.id.otpppinview);
        otpptimerdisplayer = findViewById(R.id.otppcounter);
        otppresendotp = (TextView) findViewById(R.id.otppresendotp);
        otppverifycodebutton = (Button) findViewById(R.id.otppverifycodebutton);
        otppprogressbar = (ProgressBar) findViewById(R.id.otppprogressbar);

        /*fAuth = FirebaseAuth.getInstance();
        tspfullname = (EditText) findViewById(R.id.tspfullname);
        tspage = (EditText) findViewById(R.id.tspage);
        tspsexSpinner = (Spinner) findViewById(R.id.tspsexSpinner);
        tspphonenumber =(EditText) findViewById(R.id.tspphonenumber);
        tspemail =(EditText) findViewById(R.id.tspemail);
        tspaadharnumber =(EditText) findViewById(R.id.tspaadharnumber);
        tsphometownaddress =(EditText) findViewById(R.id.tsphometownaddress);
        tspproffessiontextview = (TextView) findViewById(R.id.tspproffessiontextview);
        tspradiogroup = (RadioGroup) findViewById(R.id.tspradiogroup);
        tspstudent = findViewById(R.id.tspradiobuttonstudent);
        tspworking = findViewById(R.id.tspradiobuttonworking);
        tspcollegename = findViewById(R.id.tspcollegename);
        tspcollegeaddress = findViewById(R.id.tspcollegeaddress);
        tspcompanyname = findViewById(R.id.tspcompanyname);
        tspcompanyaddress = findViewById(R.id.tspcompanyaddress);
        tspusername =(EditText) findViewById(R.id.tspusername);
        tsppassword =(EditText) findViewById(R.id.tsppassword);
        tspconfirmpassword =(EditText) findViewById(R.id.tspconfirmpassword);*/

        Intent intent = getIntent();
        String Phonenumber = getIntent().getStringExtra("PHONENUMBER");
        otppphonenodisplayer.setText(Phonenumber);
        sendVerificationCodeToUser(Phonenumber);

        startTimer();
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
            Toast.makeText(getApplicationContext(), "OTP sent", Toast.LENGTH_SHORT).show();
            otppprogressbar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null) {
                otppcodeenteredinpinview.setText(code);
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OTP_Page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            otppprogressbar.setVisibility(View.INVISIBLE);
            return;
        }
    };

    private void verifycode(String codebyuser){
        otppprogressbar.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationcodebysystem, codebyuser);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        FirebaseAuth firebaseauth = FirebaseAuth.getInstance();
        firebaseauth.signInWithCredential(credential).addOnCompleteListener(OTP_Page.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Toast.makeText(getApplicationContext(), " Phone Authentication Successful!", Toast.LENGTH_SHORT).show();
                   fAuth.signOut();
                   Intent into = new Intent(getApplicationContext(), Login_Activity.class);
                   into.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(into);
               }
               else{
                   Toast.makeText(OTP_Page.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   otppprogressbar.setVisibility(View.INVISIBLE);
                   return;
               }
            }
        });
    }

    public void FinalVerification(View view)   {
        String cd = otppcodeenteredinpinview.getText().toString();
        if(!cd.isEmpty()){
            verifycode(cd);
        }
        else{
            otppprogressbar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
            otppprogressbar.setVisibility(View.INVISIBLE);
        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                otppresendotp.setVisibility(View.VISIBLE);

            }
        }.start();
        mTimerRunning=true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis/1000)/60;

        int seconds = (int) (mTimeLeftInMillis/1000)%60;

        String timeleftformatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        otpptimerdisplayer.setText(timeleftformatted);
    }

    public void resendotp(View view) {

        mTimeLeftInMillis = START_TIME_IN_MILIS;
        updateCountDownText();
        startTimer();
        otppresendotp.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String Phonenumber = getIntent().getStringExtra("PHONENUMBER");
        sendVerificationCodeToUser(Phonenumber);

    }

    /*private String COLLEGEORCOMPANYADDRESS() {
        if(tspstudent.isChecked()){
            return tspcollegeaddress.getText().toString();
        }
        else{
            return tspcompanyaddress.getText().toString();
        }
    }

    private String COLLEGEORCOMPANYNAME() {
        if(tspstudent.isChecked()){
            return tspcollegename.getText().toString();
        }
        else{
            return tspcompanyname.getText().toString();
        }

    }

    private String PROFFESSION() {
        if(tspstudent.isChecked()){
            return tspstudent.getText().toString();
        }
        else{
            return tspworking.getText().toString();
        }
    }*/
}