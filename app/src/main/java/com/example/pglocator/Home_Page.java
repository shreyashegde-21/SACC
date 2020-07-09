package com.example.pglocator;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.MenuItem;
import android.widget.Toast;

public class Home_Page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
private NavigationView navigationview;
private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        fAuth = FirebaseAuth.getInstance();

        Toolbar homepagetoolbar = findViewById(R.id.homepagetoolbar);
        setSupportActionBar(homepagetoolbar);

        BottomNavigationView bottomnavigationview = findViewById(R.id.homepagebottomnavigationview);
        bottomnavigationview.setOnNavigationItemSelectedListener(listener);

        drawer = findViewById(R.id.homepagedrawerlayout);
        navigationview = findViewById(R.id.homepageleftnavigationview);
        navigationview.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, homepagetoolbar,
                 R.string.navigationdraweropen, R.string.navigationdrawerclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.homepagefragmentcontainer,
                    new homefragment()).commit();
            bottomnavigationview.getMenu().findItem(R.id.home).setChecked(true);

        }

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
        bottomnavigationview.getMenu().findItem(R.id.account).setEnabled(true);
        }
        else
            {
                bottomnavigationview.getMenu().findItem(R.id.account).setEnabled(false);
            }
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.myprofile:
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.homepagefragmentcontainer,
                            new profilefragment()).commit();
                }else{
                    Toast.makeText(getApplicationContext(), "Please Login First!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add:
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), Add.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Please Login First!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_post:
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), PostActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Please Login First!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.liked:
                startActivity(new Intent(getApplicationContext(), Liked_Displayer.class));
                break;
            case R.id.likedblogs:
                startActivity(new Intent(getApplicationContext(), LikedBlogs.class));
                break;
            case R.id.signup:
                startActivity(new Intent(getApplicationContext(), ownerortenantselection.class));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), Login_Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.aboutus:
                startActivity(new Intent(getApplicationContext(), aboutus.class));
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}