package com.example.assignment2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.Classes.Item;
import com.example.assignment2.Classes.ItemAdapter;
import com.example.assignment2.Classes.User;
import com.example.assignment2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyArea#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyArea extends Fragment {

    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyArea() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyArea.
     */
    // TODO: Rename and change types and number of parameters
    public static MyArea newInstance(String param1, String param2) {
        MyArea fragment = new MyArea();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_area, container, false);

        List<User> userList = new ArrayList<>();
        List<Item> menu = new ArrayList<>();
        String username = "";

        Log.d("ACCOUNT", "bundle: " + getArguments());

// Check if arguments are not null and contain the keys
        if (getArguments() != null) {
            // Retrieve the data from the Bundle
            if (getArguments().containsKey("userList")) {
                userList = (List<User>) getArguments().getSerializable("userList");
            }
            if (getArguments().containsKey("menu")) {
                menu = (List<Item>) getArguments().getSerializable("menu");
            }
            if (getArguments().containsKey("username")) {
                username = getArguments().getString("username");
            }
        }

        TextView helloUserTextView = view.findViewById(R.id.hello_user);
        helloUserTextView.setText("שלום, " + username + "!");

        ItemAdapter adapter = new ItemAdapter(requireContext(), menu);
        recyclerView = view.findViewById(R.id.items);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(adapter);
        } else {
            Log.e("MyAreaFragment", "RecyclerView is null");
        }


        return view;
    }
}