package com.example.pglocator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LikedBlogs_Recyclerviewadapter extends RecyclerView.Adapter<LikedBlogs_Recyclerviewadapter.likedblogsViewHolder> {

    private Context ctx;
    private List<PostModelClass> pmc;

    public LikedBlogs_Recyclerviewadapter(Context ctx, List<PostModelClass> pmc) {
        this.ctx = ctx;
        this.pmc = pmc;
    }

    @NonNull
    @Override
    public LikedBlogs_Recyclerviewadapter.likedblogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.blog_row, parent, false);
        LikedBlogs_Recyclerviewadapter.likedblogsViewHolder myviewholder = new LikedBlogs_Recyclerviewadapter.likedblogsViewHolder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull likedblogsViewHolder holder,final int position) {
        holder.title.setText(pmc.get(position).getTitle());
        holder.setImage(ctx, pmc.get(position).getImage());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent into = new Intent(ctx, PostDisplayer.class);
                into.putExtra("blogtitle", pmc.get(position).getTitle());
                into.putExtra("blogdes", pmc.get(position).getDesc());
                into.putExtra("blogimg", pmc.get(position).getImage());
                into.putExtra("bloguid", pmc.get(position).getUID());
                into.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(into);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pmc.size();
    }

    class likedblogsViewHolder extends RecyclerView.ViewHolder{

        CardView mView;
        TextView title;
        ImageView mImage;

        public likedblogsViewHolder(@NonNull View itemView) {

            super(itemView);
            mView = itemView.findViewById(R.id.blog_rowid);
            title = itemView.findViewById(R.id.post_title);
        }
        public void setImage(Context ctx, String image){
            mImage = mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(mImage);
        }
    }
}
