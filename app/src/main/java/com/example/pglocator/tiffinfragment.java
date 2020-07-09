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

        tiffinservices.add(new Tiffin_Information(R.drawable.t1, "Siddhivinayak Caterers", "8456971235",
                "Dadar, Mumbai", "Ram maruti road, Near Siddhivinayak temple, Dadar.", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 7000\nNon-veg - 10000\nJain - 8500", "There will be a monthly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t2, "Sharanu Caterers", "9456712354",
                "Thane, Mumbai", "Amar Nivas, Near station road, Behind eternity mall,Thane(w).", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 6000\nNon-veg - 15000\nJain - 7500", "There will be a weekly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t3, "mahalakshmi poli bhaji kendra", "8456712395",
                "panvel, Mumbai", "shakuni nagar, mahatma road, behind sai baba temple, panvel", "Veg, Nonveg ",
                "Office Delivery.\nWeekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "More Discount offers on more quantity "));
        tiffinservices.add(new Tiffin_Information(R.drawable.t4, "Oberoi catering services", "7621459875",
                "dombivali, Mumbai", "krishna nagar, nehru road, behind durga temple, tees gaon naka", "veg and Nonveg ",
                "Weekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "we work 24*7:) "));

        tiffinservices.add(new Tiffin_Information(R.drawable.t1, "Durga caterers", "8456971235",
                "kolkata", "Ram maruti road, Near Siddhivinayak temple, Dadar.", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 7000\nNon-veg - 10000\nJain - 8500", "There will be a monthly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t2, "bhalo bashi khaana", "9456712354",
                "siliguri, kolkata", "Amar Nivas, Near station road, Behind eternity mall,Thane(w).", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 6000\nNon-veg - 15000\nJain - 7500", "There will be a weekly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t3, "ami shotti bol che food services", "8456712395",
                "asansol, kolkata", "shakuni nagar, mahatma road, behind sai baba temple, panvel", "Veg, Nonveg ",
                "Office Delivery.\nWeekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "More Discount offers on more quantity "));
        tiffinservices.add(new Tiffin_Information(R.drawable.t4, "machi khana", "7621459875",
                "durgapur, kolkata", "krishna nagar, nehru road, behind durga temple, tees gaon naka", "veg and Nonveg ",
                "Weekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "we work 24*7:) "));

        tiffinservices.add(new Tiffin_Information(R.drawable.t1, "Kasikannu catering world", "8456971235",
                "chennai", "Ram maruti road, Near Siddhivinayak temple, Dadar.", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 7000\nNon-veg - 10000\nJain - 8500", "There will be a monthly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t2, "chennai catering services", "9456712354",
                "ayanavaram, chennai", "Amar Nivas, Near station road, Behind eternity mall,Thane(w).", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 6000\nNon-veg - 15000\nJain - 7500", "There will be a weekly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t3, "namma veetu kalyanam catering", "8456712395",
                "gopalapuram, chennai", "shakuni nagar, mahatma road, behind sai baba temple, panvel", "Veg, Nonveg ",
                "Office Delivery.\nWeekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "More Discount offers on more quantity "));
        tiffinservices.add(new Tiffin_Information(R.drawable.t4, "quality caterers", "7621459875",
                "mudichur , chennai", "krishna nagar, nehru road, behind durga temple, tees gaon naka", "veg and Nonveg ",
                "Weekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "we work 24*7:) "));

        tiffinservices.add(new Tiffin_Information(R.drawable.t1, "mariamma catering", "8456971235",
                "bangalore", "Ram maruti road, Near Siddhivinayak temple, Dadar.", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 7000\nNon-veg - 10000\nJain - 8500", "There will be a monthly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t2, "banashankari food services", "9456712354",
                "jayanagar, bangalore", "Amar Nivas, Near station road, Behind eternity mall,Thane(w).", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 6000\nNon-veg - 15000\nJain - 7500", "There will be a weekly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t3, "manjunatha caterings", "8456712395",
                "basavanagudi, bangalore", "shakuni nagar, mahatma road, behind sai baba temple, panvel", "Veg, Nonveg ",
                "Office Delivery.\nWeekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "More Discount offers on more quantity "));
        tiffinservices.add(new Tiffin_Information(R.drawable.t4, "bhavna foods", "7621459875",
                "koramangala, bangalore", "krishna nagar, nehru road, behind durga temple, tees gaon naka", "veg and Nonveg ",
                "Weekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "we work 24*7:) "));

        tiffinservices.add(new Tiffin_Information(R.drawable.t1, "paji food services", "8456971235",
                "Delhi", "Ram maruti road, Near Siddhivinayak temple, Dadar.", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 7000\nNon-veg - 10000\nJain - 8500", "There will be a monthly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t2, "excellent caterers", "9456712354",
                "gurugram, delhi", "Amar Nivas, Near station road, Behind eternity mall,Thane(w).", "Veg, Nonveg and Jain too",
                "Office Delivery.\nMonthly Packages available.\nWeekly packages available.\nBreakfast, Lunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 6000\nNon-veg - 15000\nJain - 7500", "There will be a weekly off which will be informed:)"));
        tiffinservices.add(new Tiffin_Information(R.drawable.t3, "taj mahal foods", "8456712395",
                "ghaziabad, delhi", "shakuni nagar, mahatma road, behind sai baba temple, panvel", "Veg, Nonveg ",
                "Office Delivery.\nWeekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "More Discount offers on more quantity "));
        tiffinservices.add(new Tiffin_Information(R.drawable.t4, "outstanding foods", "7621459875",
                "mehrauli, delhi", "krishna nagar, nehru road, behind durga temple, tees gaon naka", "veg and Nonveg ",
                "Weekly packages available.\nLunch and Dinner served." +
                        "\nHome delivery.\nAdditional Charges on extra requirements.(Depending on food type, quantity and delivery location)\nPrior info to be provided when tiffin not needed else would need to pay the fine!",
                "on per month basis(INR):\nVeg - 10000\nNon-veg - 20000", "we work 24*7:) "));

        return view;
    }

}
