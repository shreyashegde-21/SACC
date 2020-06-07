package com.example.pglocator;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class Home_Page extends AppCompatActivity {
private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        Toolbar homepagetoolbar = findViewById(R.id.homepagetoolbar);
        setSupportActionBar(homepagetoolbar);

        drawer = findViewById(R.id.homepagedrawerlayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, homepagetoolbar,
                 R.string.navigationdraweropen, R.string.navigationdrawerclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomnavigationview = findViewById(R.id.homepagebottomnavigationview);


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}