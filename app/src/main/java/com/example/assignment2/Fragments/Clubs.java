package com.example.assignment2.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.Classes.AA_RecyclerViewAdapter;
import com.example.assignment2.Classes.DjModel;
import com.example.assignment2.Classes.realTimeDb;
import com.example.assignment2.R;
import java.util.ArrayList;
import java.util.Arrays;



public class Clubs extends Fragment implements AA_RecyclerViewAdapter.OnItemClickListener {

    RecyclerView mRecyclerView;

    public Clubs() {
        // Required empty public constructor
    }

    public static Clubs newInstance(String param1, String param2) {
        Clubs fragment = new Clubs();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_clubs, container, false);
        mRecyclerView = rootView.findViewById(R.id.mRecycler);

        ArrayList<DjModel> djs = new ArrayList<>(Arrays.asList(
                new DjModel("DramaTLV","פופ","משה כהן","Avicii - The Nights",R.drawable.club1),
                new DjModel("BlueMoon","רוק","סקאזי","Amnezia - ABCD",R.drawable.club2),
                new DjModel("שלוותא","פופ","אלון מתנה","נועה קירל - שלושה בנות",R.drawable.club3),
                new DjModel("האומן 17","פופ","בן אזולאי","Avicii - The Nights",R.drawable.club4),
                new DjModel("BlueMoon","רוק","רון לוי","Amnezia - ABCD",R.drawable.club5),
                new DjModel("Bee","פופ","אלון בן דוד","נועה קירל - שלושה בנות",R.drawable.club6),
                new DjModel("Forum B.SH.","פופ","דנה אשכנזי","Avicii - The Nights",R.drawable.club7)
        ));

        AA_RecyclerViewAdapter adapter = new AA_RecyclerViewAdapter(getActivity(), djs);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set click listener
        adapter.setOnItemClickListener(this);


        realTimeDb.fetchRandomSongFromFirebase(djs, adapter);

        return rootView;
    }

    @Override
    public void onItemClick(DjModel selectedItem) {
        // Handle item click
        navigateToExplainScreen(selectedItem);
    }

    private void navigateToExplainScreen(DjModel selectedItem) {
        // Navigate to explainScreen fragment, passing selected item data
        ExplainScreen explainScreen = ExplainScreen.newInstance(selectedItem);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, explainScreen)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realTimeDb.cancelAllTimers();
    }



}
