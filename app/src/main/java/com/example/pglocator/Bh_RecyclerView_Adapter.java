package com.example.pglocator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class Bh_RecyclerView_Adapter extends RecyclerView.Adapter<Bh_RecyclerView_Adapter.BH_ViewHolder> implements Filterable {

    Context bhcontext;
    ArrayList<addsite_helper_class> mybhhepler;
    private ArrayList<addsite_helper_class> filtermybhhelper;

    public Bh_RecyclerView_Adapter(Context c, ArrayList<addsite_helper_class> a) {
        bhcontext = c;
        mybhhepler = a;
        filtermybhhelper = new ArrayList<>(a);
    }

    @NonNull
    @Override
    public BH_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.row_item, parent, false);
        BH_ViewHolder myviewholder = new BH_ViewHolder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull BH_ViewHolder holder, final int position) {

     holder.rowtextview.setText(mybhhepler.get(position).getName());
     holder.rowtextview2.setText(mybhhepler.get(position).getCity());
     holder.setImage(bhcontext, mybhhepler.get(position).getImage());

        holder.rowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent into = new Intent(bhcontext, Search_Fragment_Displayer.class);

                into.putExtra("bhimage", mybhhepler.get(position).getImage());
                into.putExtra("bhname", mybhhepler.get(position).getName());
                into.putExtra("bhaddress", mybhhepler.get(position).getAddress());
                into.putExtra("bhcity", mybhhepler.get(position).getCity());
                into.putExtra("bhpincode", mybhhepler.get(position).getPincode());
                into.putExtra("bhphonenumber", mybhhepler.get(position).getPhoneNumber());
                into.putExtra("bhemail", mybhhepler.get(position).getEmail());
                into.putExtra("bhtype", mybhhepler.get(position).getType());
                into.putExtra("bhrentpermonth", mybhhepler.get(position).getRent_Per_Month_INR());
                into.putExtra("bhfacilitieesinvicinty", mybhhepler.get(position).getFacility_In_Vicinity());
                into.putExtra("bhfoodavail", mybhhepler.get(position).getFood_Availability());
                into.putExtra("bhfurnish", mybhhepler.get(position).getFurnishment());
                into.putExtra("bhacavail", mybhhepler.get(position).getAC_Availability());
                into.putExtra("bhroomtype", mybhhepler.get(position).getRoom_Type());

                bhcontext.startActivity(into);
            }
        });
    }

    @Override
    public int getItemCount() {
       return mybhhepler.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

private Filter exampleFilter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        ArrayList<addsite_helper_class> filteredList = new ArrayList<>();
        if(constraint == null || constraint.length() == 0){
            filteredList.addAll(filtermybhhelper);
        }else{
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (addsite_helper_class item : filtermybhhelper){
                if (item.getName().toLowerCase().contains(filterPattern)){
                    filteredList.add(item);
                }
            }
        }

        FilterResults results = new FilterResults();
        results.values = filteredList;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
    mybhhepler.clear();
    mybhhepler.addAll((Collection<? extends addsite_helper_class>) results.values);
    notifyDataSetChanged();;
    }
};

    class BH_ViewHolder extends RecyclerView.ViewHolder{

        ImageView rowimageview;
        TextView rowtextview, rowtextview2;
        ConstraintLayout rowlayout;


        public BH_ViewHolder(@NonNull View itemView) {

            super(itemView);
            rowimageview = itemView.findViewById(R.id.rowimageview);
            rowtextview = itemView.findViewById(R.id.rowtextview);
            rowtextview2 = itemView.findViewById(R.id.rowtextview2);
            rowlayout = itemView.findViewById(R.id.rowid);

        }

        public void setImage(Context bhcontext, String image) {
            Picasso.get().load(image).into(rowimageview);
        }
    }

}
