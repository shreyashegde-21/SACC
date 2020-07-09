package com.example.pglocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;

public class ownerortenantselection extends AppCompatActivity {

    private Button tsftaker, osftaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerortenantselection);
        tsftaker = (Button) findViewById(R.id.tsftaker);
        osftaker = (Button) findViewById(R.id.osftaker);

        tsftaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tenant_Signup_Form.class));
            }
        });

        osftaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Owner_Signup_Form.class));
            }
        });
    }
}