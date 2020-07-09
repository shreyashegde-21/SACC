package com.example.pglocator;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class tiffinfragment extends Fragment {

    RecyclerView recyclerview ;
    TF_RecyclerView_Adapter recycleradapter;
    private FirebaseDatabase fData;
    List<Tiffin_Information> tiffinservices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiffin, container, false);

        tiffinservices = new ArrayList<>();
        recyclerview = view.findViewById(R.id.recyclerView);
        recycleradapter = new TF_RecyclerView_Adapter(getContext(), tiffinservices);
        recyclerview.setAdapter(recycleradapter);


        DividerItemDecoration divideritemdecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerview.addItemDecoration(divideritemdecoration);

        tiffinservices.add(new Tiffin_Information(R.drawable.tiffinimage, "oberoi catering services", "9029512341",
                "andheri", "b1,703,anand krupa tower, hajuri road,thane.", "veg along with egg diishes",
                "we provide an ample variety of food types and dishes which will change your taste buds",
                "100-25k", "we dont work on sundays:)"));

        return view;
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter, menu);

        MenuItem searchitem = menu.findItem(R.id.searchfilter);
        SearchView searchview = (SearchView) searchitem.getActionView();

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recycleradapter.getFilter().filter(s);
                return false;
            }
        });
        return;
    }*/


}
