package com.example.pglocator;

import android.app.Activity;
import android.app.Application;

import com.firebase.client.Firebase;

public class pgfirebase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
