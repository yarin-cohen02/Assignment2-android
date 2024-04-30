package com.example.assignment2.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.R;

public class SongRequestDialogFragment {
    public static void showDialog(Context context, String name, String clubName) {
        // Create a builder for the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflate the layout for the dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.request_popup, null);

        // Find views in the dialog layout
        EditText songEditText = dialogView.findViewById(R.id.songEditText);
        TextView nameTextView = dialogView.findViewById(R.id.nameTextView);
        TextView clubNameTextView = dialogView.findViewById(R.id.clubNameTextView);

        // Set text values
        nameTextView.setText(name);
        clubNameTextView.setText(clubName);

        // Set up dialog behavior
        AlertDialog dialog = builder.setView(dialogView)
                // Change the "Submit" button text to Hebrew
                .setPositiveButton("שלח", null) // Do not set the click listener yet
                // Change the "Cancel" button text to Hebrew
                .setNegativeButton("בטל", null)
                .create();

        // Override the positive button click listener after creating the dialog
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button submitButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String songRequest = songEditText.getText().toString();
                        // Check if the song request is empty
                        if (!TextUtils.isEmpty(songRequest)) {
                            // Handle song request submission here
                            // Show toast message after submitting
                            Toast.makeText(context, "הבקשה נשלחה", Toast.LENGTH_SHORT).show();
                            dialog.dismiss(); // Close the dialog after submitting
                        } else {
                            // If the song request is empty, show a toast message
                            Toast.makeText(context, "יש להזין שם שיר", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Show the dialog
        dialog.show();
    }
}
