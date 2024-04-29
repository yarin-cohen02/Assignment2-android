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

    private Context context;
    private ArrayList<DjModel> djModels;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(DjModel selectedItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AA_RecyclerViewAdapter(Context context, ArrayList<DjModel> djModels) {
        this.context = context;
        this.djModels = djModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(djModels.get(position));
                }
            }
        });

        // Bind other data to views
        holder.tvName.setText(djModels.get(position).getDjName());
        holder.tvClub.setText(djModels.get(position).getDjClub());
        holder.tvNowPlaying.setText(djModels.get(position).getNowPlaying());
        holder.imageView.setImageResource(djModels.get(position).getDjImg());
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
