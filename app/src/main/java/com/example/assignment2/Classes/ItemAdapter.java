package com.example.assignment2.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final List<Item> itemList;
    private final Context context;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textViewName.setText(item.getName());
        holder.textViewPrice.setText(String.valueOf(item.getPrice() + " ₪"));
        holder.selectQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuantityDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPrice;
        Button selectQuantityButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            selectQuantityButton = itemView.findViewById(R.id.selectQuantityButton);
        }
    }

    private void showQuantityDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Quantity");

        final Item item = itemList.get(position);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_quantity, null);
        final TextView quantityTextView = view.findViewById(R.id.quantityTextView);
        Button incrementButton = view.findViewById(R.id.incrementButton);
        Button decrementButton = view.findViewById(R.id.decrementButton);

        quantityTextView.setText(String.valueOf(item.getPrice()));

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = item.getQuantity();
                item.setQuantity(currentQuantity + 1);
                quantityTextView.setText(String.valueOf(item.getQuantity()));
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = item.getQuantity();
                if (currentQuantity > 0) {
                    item.setQuantity(currentQuantity - 1);
                    quantityTextView.setText(String.valueOf(item.getQuantity()));
                }
            }
        });

        builder.setView(view);
        builder.setPositiveButton("OK", null); // You can add actions if needed
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
