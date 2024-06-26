package com.example.assignment2.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.Activities.InnerAppActivity;
import com.example.assignment2.Classes.DjModel;
import com.example.assignment2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExplainScreen extends Fragment {

    private static final String ARG_SELECTED_DJ = "selectedDj";

    private DjModel selectedDj;

    public ExplainScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedDj The selected DJ model.
     * @return A new instance of fragment ExplainScreen.
     */
    public static ExplainScreen newInstance(DjModel selectedDj) {
        ExplainScreen fragment = new ExplainScreen();
        Bundle args = new Bundle();
        args.putParcelable(ARG_SELECTED_DJ, selectedDj);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedDj = getArguments().getParcelable(ARG_SELECTED_DJ);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_explain_screen, container, false);

        // Find ImageView and TextViews by their IDs
        ImageView artistImgImageView = rootView.findViewById(R.id.artistImg);
        TextView nameTextView = rootView.findViewById(R.id.name);
        TextView clubNameTextView = rootView.findViewById(R.id.clubName);
        TextView currentSongTextView = rootView.findViewById(R.id.currentSong);

        // Set image and text values using data from selectedDj object
        if (selectedDj != null) {
            artistImgImageView.setImageResource(selectedDj.getDjImg());
            nameTextView.setText(selectedDj.getDjName());
            clubNameTextView.setText(selectedDj.getDjClub());
            currentSongTextView.setText(selectedDj.getNowPlaying());
        }

        // Find suggestButton by its ID
        Button suggestButton = rootView.findViewById(R.id.suggestButton);
        suggestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text values from TextViews
                String name = nameTextView.getText().toString();
                String clubName = clubNameTextView.getText().toString();

                // Open the dialog
                openSongRequestDialog(name, clubName);
            }
        });

        // Find exitBtn ImageView by its ID
        ImageView exitBtnImageView = rootView.findViewById(R.id.exitBtn);
        exitBtnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Home fragment
                InnerAppActivity innerAppActivity = (InnerAppActivity) getActivity();
                if (innerAppActivity != null) {
                    innerAppActivity.replaceFragment(new Home());
                    innerAppActivity.selectHome();
                }
            }
        });

        return rootView;
    }

    // Method to open the song request dialog
    private void openSongRequestDialog(String name, String clubName) {
        // Get the hosting Activity's context
        InnerAppActivity activity = (InnerAppActivity) getActivity();

        // Check if the activity is available
        if (activity != null) {
            // Call the method in the activity to show the dialog
            activity.showSongRequestDialog(name, clubName);
        }
    }
}
