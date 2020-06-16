package com.example.pglocator;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView registerpagetaker, homepagetaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        registerpagetaker = findViewById(R.id.registerationlink);
        homepagetaker = findViewById(R.id.homepagelink);
        homepagetaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Login_Activity.this, Home_Page.class);
                startActivity(int1);
            }
        });

    }

    public void showpopup(View view) {
        PopupMenu ownerortenant = new PopupMenu(this, view);
        ownerortenant.setOnMenuItemClickListener(this);
        ownerortenant.inflate(R.menu.popup_menu_tenant_or_owner);
        ownerortenant.show();
        
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.tenant:
                registerpagetaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent into = new Intent(Login_Activity.this, Tenant_Signup_Form.class);
                        startActivity(into);
                    }
                });
                return true;

            case R.id.owner:
                registerpagetaker.setOnClickListener(new View.OnClickListener() {
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