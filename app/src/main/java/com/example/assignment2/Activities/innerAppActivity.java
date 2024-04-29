package com.example.assignment2.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment2.Fragments.ExplainScreen;
import com.example.assignment2.Fragments.Home;
import com.example.assignment2.Fragments.Login;
import com.example.assignment2.R;
import com.example.assignment2.databinding.ActivityInnerAppBinding;

public class innerAppActivity extends AppCompatActivity {

    ActivityInnerAppBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInnerAppBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        replaceFragment(new Home());

        // Set listeners of tapping the navBar items:
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();  // Instead of CASES

            if (itemId == R.id.homeItem) {
                replaceFragment(new Home());
            } else if (itemId == R.id.clubsItem) {
                replaceFragment(new ExplainScreen());
            } else if (itemId == R.id.accountItem) {
                replaceFragment(new Login());
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