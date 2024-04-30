package com.example.assignment2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment2.Activities.InnerAppActivity;
import com.example.assignment2.Activities.MainActivity;
import com.example.assignment2.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView email = view.findViewById(R.id.email);
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        email.setText(currentUser);

        view.findViewById(R.id.signoutBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SignOut from Firebase Service
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.signOut();
                } else {
                    Log.e("LoginFragment", "Unable to get MainActivity instance");
                }

                // Navigate to innerAppActivity
                InnerAppActivity innerAppActivity = (InnerAppActivity) getActivity();
                if (innerAppActivity != null) {
                    innerAppActivity.goToMainActivity();
                } else {
                    Log.e("LoginFragment", "Unable to get MainActivity instance");
                }
            }
        });

        return view;
    }
}