package com.example.pglocator;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

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
        bottomnavigationview.setOnNavigationItemSelectedListener(listener);
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

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfragment = null;
                    switch(menuItem.getItemId()){

                        case R.id.tiffin:
                            selectedfragment = new tiffinfragment();
                            break;

                        case R.id.search:
                            selectedfragment = new searchfragment();
                            break;

                        case R.id.home:
                            selectedfragment = new homefragment();
                            break;

                        case R.id.wallet:
                            selectedfragment = new walletfragment();
                            break;

                        case R.id.account:
                            selectedfragment = new profilefragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.homepagefragmentcontainer,
                            selectedfragment).commit();
                    return true;
                }
            };
}