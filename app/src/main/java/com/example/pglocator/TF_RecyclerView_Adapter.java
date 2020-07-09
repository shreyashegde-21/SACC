package com.example.pglocator;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TF_RecyclerView_Adapter extends RecyclerView.Adapter<TF_RecyclerView_Adapter.TF_ViewHolder>{

    private Context mcontext;
    List<Tiffin_Information> tiffinservices;

    public TF_RecyclerView_Adapter(Context mcontext, List<Tiffin_Information> tiffinservices) {
        this.mcontext = mcontext;
        this.tiffinservices = tiffinservices;
    }

    @NonNull
    @Override

    public TF_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutinflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutinflater.inflate(R.layout.row_item, viewGroup, false);
        TF_ViewHolder viewholder = new TF_ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull TF_ViewHolder viewHolder, final int i) {

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

    class TF_ViewHolder extends RecyclerView.ViewHolder {

        ImageView rowimageview;
        TextView rowtextview, rowtextview2;
        ConstraintLayout rowlayout;

        public TF_ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowimageview = itemView.findViewById(R.id.rowimageview);
            rowtextview = itemView.findViewById(R.id.rowtextview);
            rowtextview2 = itemView.findViewById(R.id.rowtextview2);
            rowlayout = itemView.findViewById(R.id.rowid);
        }

    }
}
