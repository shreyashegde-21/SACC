package com.example.pglocator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class tiffinfragment extends Fragment {


    RecyclerView recyclerview ;
    RecyclerAdapter recycleradapter;
    List<Tiffin_Information> tiffinservices;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiffin, container, false);

        tiffinservices = new ArrayList<>();
        recyclerview = view.findViewById(R.id.recyclerView);
        recycleradapter = new RecyclerAdapter(getContext(), tiffinservices);
        recyclerview.setAdapter(recycleradapter);


        DividerItemDecoration divideritemdecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(divideritemdecoration);

        tiffinservices.add(new Tiffin_Information(R.drawable.tiffinimage, "oberoi catering services", "9029512341",
                "andheri", "b1,703,anand krupa tower, hajuri road,thane.", "veg along with egg diishes",
                "we provide an ample variety of food types and dishes which will change your taste buds",
                "100-25k", "we dont work on sundays:)"));


        return view;
    }
}
