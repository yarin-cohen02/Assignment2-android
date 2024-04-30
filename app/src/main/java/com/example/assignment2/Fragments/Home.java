package com.example.assignment2.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.assignment2.Classes.AA_RecyclerViewAdapter;
import com.example.assignment2.Classes.DjModel;
import com.example.assignment2.R;
import java.util.ArrayList;
import java.util.Arrays;

public class Home extends Fragment implements AA_RecyclerViewAdapter.OnItemClickListener {

    RecyclerView mRecyclerView;

    public Home() {
        // Required empty public constructor
    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = rootView.findViewById(R.id.mRecycler);

        ArrayList<DjModel> djs = new ArrayList<>(Arrays.asList(
                new DjModel("משה כהן","פופ","DramaTLV","Avicii - The Nights",R.drawable.dj1),
                new DjModel("סקאזי","רוק","BlueMoon","Amnezia - ABCD",R.drawable.dj2),
                new DjModel("אלון מתנה","פופ","PortTLV","נועה קירל - שלושה בנות",R.drawable.dj3),
                new DjModel("בן אזולאי","פופ","DramaTLV","Avicii - The Nights",R.drawable.dj4),
                new DjModel("רון לוי","רוק","BlueMoon","Amnezia - ABCD",R.drawable.dj5),
                new DjModel("אלון בן דוד","פופ","PortTLV","נועה קירל - שלושה בנות",R.drawable.dj6),
                new DjModel("דנה אשכנזי","פופ","DramaTLV","Avicii - The Nights",R.drawable.dj7),
                new DjModel("דה ספייס","רוק","BlueMoon","Amnezia - ABCD",R.drawable.images),
                new DjModel("דוד חי","פופ","PortTLV","נועה קירל - שלושה בנות",R.drawable.dj1)
        ));

        AA_RecyclerViewAdapter adapter = new AA_RecyclerViewAdapter(getActivity(), djs);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set click listener
        adapter.setOnItemClickListener(this);

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
}
