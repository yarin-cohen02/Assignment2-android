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

public class AA_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_AD = 1;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == VIEW_TYPE_NORMAL) {
            View view = inflater.inflate(R.layout.recycler_row, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.ad_row, parent, false);
            return new AdViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == VIEW_TYPE_NORMAL) {
            final DjModel currentItem = djModels.get(getRealPosition(position));
            MyViewHolder normalViewHolder = (MyViewHolder) holder;
            // Bind data for normal item...
            normalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(currentItem);
                    }
                }
            });
            normalViewHolder.tvName.setText(currentItem.getDjName());
            normalViewHolder.tvClub.setText(currentItem.getDjClub());
            normalViewHolder.tvNowPlaying.setText(currentItem.getNowPlaying());
            normalViewHolder.imageView.setImageResource(currentItem.getDjImg());
        } else {
            // Handle ad item if needed
        }
    }

    @Override
    public int getItemCount() {
        return djModels.size() + djModels.size() / 3; // Add ad items to the count
    }

    @Override
    public int getItemViewType(int position) {
        return (position + 1) % 4 == 0 ? VIEW_TYPE_AD : VIEW_TYPE_NORMAL;
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

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder for ad item if needed

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize ad item views if needed
        }
    }

    private int getRealPosition(int position) {
        return position - (position + 1) / 4;
    }
}
