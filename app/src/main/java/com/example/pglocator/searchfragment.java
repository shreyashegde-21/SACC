package com.example.pglocator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class searchfragment extends Fragment {

    private Button sfbhbutton, sfghButton, sfbpgbutton, sfgpgbutton, sfrentbutton;
    private  CharSequence citylist[] = {"Bangalore", "Chennai", "Delhi", "Kolkata", "Mumbai"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        sfbhbutton = view.findViewById(R.id.sfbhbutton);
        sfghButton = view.findViewById(R.id.sfghbutton);
        sfbpgbutton = view.findViewById(R.id.sfbpgbutton);
        sfgpgbutton = view.findViewById(R.id.sfgpgbutton);
        sfrentbutton = view.findViewById(R.id.sfrentbutton);


        sfbhbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder biuldr = new AlertDialog.Builder(getContext());
                biuldr.setTitle("Select City");
                biuldr.setSingleChoiceItems(citylist, -1, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                      String cityid = (String) citylist[which];
                      dialog.dismiss();
                       Intent into = new Intent(getContext(), Boys_Hostel_Displayer.class);
                       into.putExtra("buttonid", "Boys Hostel");
                       into.putExtra("cityid", cityid);
                       startActivity(into);
                   }
               });
                biuldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertdialogue = biuldr.create();
                alertdialogue.show();
            }
        });
        sfghButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder biuldr = new AlertDialog.Builder(getContext());
                biuldr.setTitle("Select City");
                biuldr.setSingleChoiceItems(citylist, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityid = (String) citylist[which];
                        dialog.dismiss();
                        Intent into = new Intent(getContext(), Boys_Hostel_Displayer.class);
                        into.putExtra("buttonid", "Girls Hostel");
                        into.putExtra("cityid", cityid);
                        startActivity(into);
                    }
                });
                biuldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertdialogue = biuldr.create();
                alertdialogue.show();
            }
        });
        sfbpgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder biuldr = new AlertDialog.Builder(getContext());
                biuldr.setTitle("Select City");
                biuldr.setSingleChoiceItems(citylist, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityid = (String) citylist[which];
                        dialog.dismiss();
                        Intent into = new Intent(getContext(), Boys_Hostel_Displayer.class);
                        into.putExtra("buttonid", "Boys PG");
                        into.putExtra("cityid", cityid);
                        startActivity(into);
                    }
                });
                biuldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertdialogue = biuldr.create();
                alertdialogue.show();
            }
        });
        sfgpgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder biuldr = new AlertDialog.Builder(getContext());
                biuldr.setTitle("Select City");
                biuldr.setSingleChoiceItems(citylist, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityid = (String) citylist[which];
                        dialog.dismiss();
                        Intent into = new Intent(getContext(), Boys_Hostel_Displayer.class);
                        into.putExtra("buttonid", "Girls PG");
                        into.putExtra("cityid", cityid);
                        startActivity(into);
                    }
                });
                biuldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertdialogue = biuldr.create();
                alertdialogue.show();
            }
        });
        sfrentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder biuldr = new AlertDialog.Builder(getContext());
                biuldr.setTitle("Select City");
                biuldr.setSingleChoiceItems(citylist, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cityid = (String) citylist[which];
                        dialog.dismiss();
                        Intent into = new Intent(getContext(), Boys_Hostel_Displayer.class);
                        into.putExtra("buttonid", "Flat/House for Rent");
                        into.putExtra("cityid", cityid);
                        startActivity(into);
                    }
                });
                biuldr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertdialogue = biuldr.create();
                alertdialogue.show();
            }
        });

         return view;
    }
}
