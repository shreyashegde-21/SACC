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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context ctx;
    private List<PostModelClass> pmc;

    public PostAdapter(Context c, ArrayList<PostModelClass> a) {
        ctx = c;
        pmc = a;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view = layoutinflater.inflate(R.layout.blog_row, parent, false);
        PostAdapter.PostViewHolder myviewholder = new PostAdapter.PostViewHolder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, final int position) {
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

    class PostViewHolder extends RecyclerView.ViewHolder{

        CardView mView;
        TextView title;
        ImageView mImage;

        public PostViewHolder(@NonNull View itemView) {

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
