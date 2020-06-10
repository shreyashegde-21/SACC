package com.example.pglocator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class tiffinfragment extends Fragment {


    RecyclerView recyclerview ;
    RecyclerAdapter recycleradapter;
    List<String> tiffinservices;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiffin, container, false);

        tiffinservices = new ArrayList<>();
        recyclerview = view.findViewById(R.id.recyclerView);
        recycleradapter = new RecyclerAdapter(tiffinservices);
        recyclerview.setAdapter(recycleradapter);

        DividerItemDecoration divideritemdecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(divideritemdecoration);

        tiffinservices.add("Ram Nivas Bhojan Bhandar");
        tiffinservices.add("Shreyas Tiffin Services");
        tiffinservices.add("Quality caterers");
        tiffinservices.add("Raghavendra Caterers");
        tiffinservices.add("RamManohar Tiffin Services");
        tiffinservices.add("Oberoi Caterers");
        tiffinservices.add("Goenka Hospitality");
        tiffinservices.add("Singhania Foods");
        tiffinservices.add("International Services");
        tiffinservices.add("Food Services");
        tiffinservices.add("Pookichat caterers");



        return view;
    }
}
