package com.example.pglocator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context mcontext;
    List<Tiffin_Information> tiffinservices;
   //  List<Tiffin_Information> tiffinservicescopy;

    public RecyclerAdapter(Context mcontext, List<Tiffin_Information> tiffinservices) {
        this.mcontext = mcontext;
        this.tiffinservices = tiffinservices;
        // this.tiffinservicescopy = new ArrayList<>(tiffinservices);
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutinflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutinflater.inflate(R.layout.row_item, viewGroup, false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.rowtextview.setText(tiffinservices.get(i).getTsname());
        viewHolder.rowtextview2.setText(tiffinservices.get(i).getTslocation());
        viewHolder.rowimageview.setImageResource(tiffinservices.get(i).getThumbnail());

        viewHolder.rowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent into = new Intent(mcontext, Tiffin_Service_Displayer.class);

                into.putExtra("tsthumbnail", tiffinservices.get(i).getThumbnail());
                into.putExtra("tsname", tiffinservices.get(i).getTsname());
                into.putExtra("tsphonenumber", tiffinservices.get(i).getTsphonenumber());
                into.putExtra("tslocation", tiffinservices.get(i).getTslocation());
                into.putExtra("address", tiffinservices.get(i).getAddress());
                into.putExtra("tsfoodtype", tiffinservices.get(i).getTsfoodtype());
                into.putExtra("tsdescription", tiffinservices.get(i).getTsdescription());
                into.putExtra("tsprices", tiffinservices.get(i).getTsprices());
                into.putExtra("tsadditionalinfo", tiffinservices.get(i).getTsadditionalinfo());

                mcontext.startActivity(into);
            }
        });
    }

    @Override
    public int getItemCount() {

        return tiffinservices.size();
    }

   /* @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Tiffin_Information> filteredlist = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredlist.addAll(tiffinservicescopy);
            }
            else{
                String filterpattern = constraint.toString().toLowerCase();

                for(Tiffin_Information item:tiffinservicescopy){
                    if(item.getTsname().toLowerCase().contains(filterpattern)){
                        filteredlist.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        tiffinservices.clear();
        tiffinservices.addAll((Collection<? extends Tiffin_Information>) results.values);
        notifyDataSetChanged();
        }
    };*/


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView rowimageview;
        TextView rowtextview, rowtextview2;
        ConstraintLayout rowlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowimageview = itemView.findViewById(R.id.rowimageview);
            rowtextview = itemView.findViewById(R.id.rowtextview);
            rowtextview2 = itemView.findViewById(R.id.rowtextview2);
            rowlayout = itemView.findViewById(R.id.rowid);
        }

    }
}
