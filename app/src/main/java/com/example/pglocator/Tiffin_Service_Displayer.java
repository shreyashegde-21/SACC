package com.example.pglocator;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Tiffin_Service_Displayer extends AppCompatActivity {

    private ImageView tsimg;
    private TextView tsname;
    private TextView tsphonenumber;
    private TextView tslocation;
    private TextView tsaddress;
    private TextView tsfoodtype;
    private TextView tsdescription;
    private TextView tsprices;
    private TextView tsadditionalinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiffin_service_displayer_activity);

        tsimg = (ImageView) findViewById(R.id.gallery);
        tsname = (TextView) findViewById(R.id.tiffinservicename);
        tsphonenumber = (TextView) findViewById(R.id.tsphonenumber);
        tslocation = (TextView) findViewById(R.id.tslocation);
        tsaddress = (TextView) findViewById(R.id.tsaddress);
        tsfoodtype = (TextView) findViewById(R.id.tsfoodtype);
        tsdescription = (TextView) findViewById(R.id.tsdescription);
        tsprices = (TextView) findViewById(R.id.tsprices);
        tsadditionalinfo = (TextView) findViewById(R.id.tsadditiveinfo);


        Intent intent = getIntent();
        int img =intent.getExtras().getInt("tsthumbnail");
        String name = intent.getExtras().getString("tsname");
        String phonenumber = intent.getExtras().getString("tsphonenumber");
        String location = intent.getExtras().getString("tslocation");
        String address = intent.getExtras().getString("address");
        String foodtype = intent.getExtras().getString("tsfoodtype");
        String description = intent.getExtras().getString("tsdescription");
        String prices = intent.getExtras().getString("tsprices");
        String additionalinfo= intent.getExtras().getString("tsadditionalinfo");

        tsimg.setImageResource(img);
        tsname.setText(name);
        tsphonenumber.setText(phonenumber);
        tslocation.setText(location);
        tsaddress.setText(address);
        tsfoodtype.setText(foodtype);
        tsdescription.setText(description);
        tsprices.setText(prices);
        tsadditionalinfo.setText(additionalinfo);
    }
}
