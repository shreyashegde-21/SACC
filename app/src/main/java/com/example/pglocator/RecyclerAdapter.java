package com.example.pglocator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "recycleradapter";
    List<String> tiffinservices;
    public RecyclerAdapter(List<String> tiffinservices) {
        this.tiffinservices = tiffinservices;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.rowtextview.setText(tiffinservices.get(i));

    }

    @Override
    public int getItemCount() {

        return tiffinservices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        ImageView rowimageview;
        TextView rowtextview, rowtextview2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowimageview = itemView.findViewById(R.id.rowimageview);
            rowtextview = itemView.findViewById(R.id.rowtextview);
            rowtextview2 = itemView.findViewById(R.id.rowtextview2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), tiffinservices.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}
