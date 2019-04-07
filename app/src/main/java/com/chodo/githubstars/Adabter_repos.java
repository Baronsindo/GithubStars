package com.chodo.githubstars;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adabter_repos extends RecyclerView.Adapter<Adabter_repos.reposViewHolder> {

    List<Class_Repos> reposList;
    Context context;

    public static class reposViewHolder extends RecyclerView.ViewHolder {

        CardView cardview;
        TextView TvRepoName;
        TextView TvRepoDesc;
        TextView TvOwner;
        TextView TvNbrStar;
        ImageView Star;
        ImageView IvOwner;


        // hada constrcutor dyal had lclass Kiyakhed Layout dyal l card
        reposViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            TvRepoName = (TextView) itemView.findViewById(R.id.TvRepoName);
            TvRepoDesc = (TextView) itemView.findViewById(R.id.TvRepoDesc);
            TvOwner = (TextView) itemView.findViewById(R.id.TvOwner);
            TvNbrStar = (TextView) itemView.findViewById(R.id.TvNbrStar);
            Star = (ImageView) itemView.findViewById(R.id.Star);
            IvOwner = (ImageView) itemView.findViewById(R.id.IvOwner);

        }
    }


    Adabter_repos(List<Class_Repos> reposList) {
        this.reposList = reposList;
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    @Override
    public reposViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_repos, viewGroup, false);
        reposViewHolder pvh = new reposViewHolder(v);
        context = viewGroup.getContext();
        // you can initilise database here;
        return pvh;
    }

    @Override
    public void onBindViewHolder(reposViewHolder reposViewHolder, int i) {
        final reposViewHolder svh = reposViewHolder;
        final int index = i;
        // use svh to acces cardview elements | i is the index
        svh.TvRepoName.setText(reposList.get(i).getName());
        svh.TvRepoDesc.setText(reposList.get(i).getDesc());
        svh.TvOwner.setText(reposList.get(i).getOwner());
        svh.TvNbrStar.setText(reposList.get(i).getStars());
        Picasso.with(context).load(reposList.get(i).getImage_Url()).into(svh.IvOwner);

        reposViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement When click on the list 
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
    