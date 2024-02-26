package com.example.assignment2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.assignment2.Classes.Item;
import com.example.assignment2.Classes.User;
import com.example.assignment2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    List<User> userList = new ArrayList<>();
    List<Item> menu = new ArrayList<Item>() {{
        add(new Item("שולחן צבעוני", 3));
        add(new Item("כיסא בר", 2));
        add(new Item("כורסא דגם עור", 1));
    }};

    private boolean isUsernameTaken(String username) {

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;  // Username is already taken
            }
        }
        return false;  // Username is available

    }


    public boolean registerUser(String username, String password) {

        // Check if the username is already taken
        if (isUsernameTaken(username)) {
            return false;  // Username is already taken, registration failed
        }

        // Register the user
        User newUser = new User(username, password);
        userList.add(newUser);
        return true;  // Registration successful

    }

    public boolean isValidate(String username, String password) {

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;  // Authentication successful
            }
        }
        return false;  // Invalid username or password

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        registerUser("admin","admin");

        // Continue Button
        view.findViewById(R.id.continueBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usernameEditText = view.findViewById(R.id.username);
                EditText passwordEditText = view.findViewById(R.id.password);

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Log the username
                Log.d("ACCOUNT", "Username: " + username);

                if (isValidate(username, password)) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userList", (Serializable) userList);
                    bundle.putString("username", username);
                    bundle.putSerializable("menu", (Serializable) menu);

                    // Navigate to myArea if the credentials are correct
                    Navigation.findNavController(view).navigate(R.id.action_login_to_myArea, bundle);

                } else {
                    // Show a message or handle the case when credentials are incorrect
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Register Button
        view.findViewById(R.id.imNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("userList", (Serializable) userList);
                bundle.putSerializable("menu", (Serializable) menu);
                Navigation.findNavController(view).navigate(R.id.action_login_to_signup, bundle);

            }
        });

        return view;
    }
}