package com.example.assignment2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.assignment2.Fragments.Login;
import com.example.assignment2.Fragments.MyArea;
import com.example.assignment2.Fragments.Signup;
import com.example.assignment2.R;
import com.example.assignment2.databinding.ActivityInnerAppBinding; // Import the correct binding class

public class InnerApp extends AppCompatActivity {

    private ActivityInnerAppBinding binding; // Declare the binding variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInnerAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new Login());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId(); // Store the item ID in a local variable

            if (itemId == R.id.home) {
                replaceFragment(new Login());
            } else if (itemId == R.id.clubs) {
                replaceFragment(new MyArea());
            } else if (itemId == R.id.settings) {
                replaceFragment(new Signup());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
