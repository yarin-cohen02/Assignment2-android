package com.example.assignment2.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;

import java.util.ArrayList;

public class AA_RecyclerViewAdapter extends RecyclerView.Adapter<AA_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<DjModel> djModels;

    public AA_RecyclerViewAdapter(Context context, ArrayList<DjModel> djModels) {
        this.context = context;
        this.djModels = djModels;
    }

    @NonNull
    @Override
    public AA_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new AA_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AA_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tvName.setText(djModels.get(position).getDjName());
        holder.tvClub.setText(djModels.get(position).getDjClub());
        holder.tvNowPlaying.setText(djModels.get(position).getNowPlaying());
//        holder.imageView.setImageResource(djModels.get(position).getDjImg());

    }

    @Override
    public int getItemCount() {
        return djModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName, tvClub, tvNowPlaying;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.heading_ad);
            tvClub = itemView.findViewById(R.id.desc_ad);
            tvNowPlaying = itemView.findViewById(R.id.nowPlaying);

        }
    }
}
