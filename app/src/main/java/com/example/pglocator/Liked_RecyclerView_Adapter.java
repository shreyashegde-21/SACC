package com.example.pglocator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Liked_RecyclerView_Adapter extends RecyclerView.Adapter<Liked_RecyclerView_Adapter.Liked_ViewHolder>{

    Context likedcontext;
    ArrayList<addsite_helper_class> likedhepler;

    public Liked_RecyclerView_Adapter(Context c, ArrayList<addsite_helper_class> a) {
        likedcontext = c;
        likedhepler = a;
    }

    @NonNull
    @Override
    public Liked_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.row_item, parent, false);
        Liked_ViewHolder myviewholder = new Liked_ViewHolder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Liked_ViewHolder holder, final int position) {

     holder.rowtextview.setText(likedhepler.get(position).getName());
     holder.rowtextview2.setText(likedhepler.get(position).getCity());
        holder.setImage(likedcontext, likedhepler.get(position).getImage());

        holder.rowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent into = new Intent(likedcontext, Search_Fragment_Displayer.class);

                into.putExtra("bhimage", likedhepler.get(position).getImage());
                into.putExtra("bhname", likedhepler.get(position).getName());
                into.putExtra("bhaddress", likedhepler.get(position).getAddress());
                into.putExtra("bhcity", likedhepler.get(position).getCity());
                into.putExtra("bhpincode", likedhepler.get(position).getPincode());
                into.putExtra("bhphonenumber", likedhepler.get(position).getPhoneNumber());
                into.putExtra("bhemail", likedhepler.get(position).getEmail());
                into.putExtra("bhtype", likedhepler.get(position).getType());
                into.putExtra("bhrentpermonth", likedhepler.get(position).getRent_Per_Month_INR());
                into.putExtra("bhfacilitieesinvicinty", likedhepler.get(position).getFacility_In_Vicinity());
                into.putExtra("bhfoodavail", likedhepler.get(position).getFood_Availability());
                into.putExtra("bhfurnish", likedhepler.get(position).getFurnishment());
                into.putExtra("bhacavail", likedhepler.get(position).getAC_Availability());
                into.putExtra("bhroomtype", likedhepler.get(position).getRoom_Type());

                likedcontext.startActivity(into);
            }
        });
    }

    @Override
    public int getItemCount() {
       return likedhepler.size();
    }

    class Liked_ViewHolder extends RecyclerView.ViewHolder{

        ImageView rowimageview;
        TextView rowtextview, rowtextview2;
        ConstraintLayout rowlayout;

        public Liked_ViewHolder(@NonNull View itemView) {

            super(itemView);
            rowimageview = itemView.findViewById(R.id.rowimageview);
            rowtextview = itemView.findViewById(R.id.rowtextview);
            rowtextview2 = itemView.findViewById(R.id.rowtextview2);
            rowlayout = itemView.findViewById(R.id.rowid);

        }
        public void setImage(Context context, String image) {
            Picasso.get().load(image).into(rowimageview);


        }
    }

}
