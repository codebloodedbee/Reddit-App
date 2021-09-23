package com.hizkeel.tredditapp.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hizkeel.tredditapp.R;
import com.hizkeel.tredditapp.ui.RedditItemDetails;
import com.hizkeel.tredditapp.model.Model;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    ArrayList<Model> data = new ArrayList<>();

    Context mContext;

    public AdapterClass(ArrayList<Model> data) {
        this.data = data;

    }


    @NonNull
//    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null));
    }

    @Override
    public void onBindViewHolder(AdapterClass.ViewHolder holder, int position ) {

        holder.title.setText(data.get(position).title);
        holder.selftext.setText(data.get(position).selftext.toString());
        holder.score.setText(data.get(position).score.toString());





        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, data.get(position).name + " is clicked", Toast.LENGTH_SHORT).show();

                Log.i("ada", "clicked"+data.get(position).title);

                final Intent it = new Intent(v.getContext(), RedditItemDetails.class);

                it.putExtra("subreddit", data.get(position).subreddit.toString());
                it.putExtra("title", data.get(position).title.toString());
                it.putExtra("selftext", data.get(position).selftext.toString());
                it.putExtra("score", data.get(position).score.toString());
                it.putExtra("ups", data.get(position).ups.toString());
                it.putExtra("downs", data.get(position).downs.toString());
                it.putExtra("created", data.get(position).created.toString());
                it.putExtra("author", data.get(position).author.toString());
                it.putExtra("num_comments", data.get(position).num_comments.toString());
                it.putExtra("url", data.get(position).url.toString());

                it.putExtra("positionGroup", position);
                v.getContext().startActivity(it);

            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Log.i("ada", "long clicked"+data.get(position).title);
                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subreddit, selftext,ups,score,created,author,num_comments,url,downs;
        LinearLayout layout;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            selftext = itemView.findViewById(R.id.item_selftext);
            score = itemView.findViewById(R.id.item_score);

            layout = itemView.findViewById(R.id.layout);





        }
    }
}

