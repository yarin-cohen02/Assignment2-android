package com.example.assignment2.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignment2.Fragments.AccountFragment;
import com.example.assignment2.Fragments.Home;
import com.example.assignment2.Fragments.Login;
import com.example.assignment2.Fragments.Clubs;
import com.example.assignment2.Fragments.SongRequestDialogFragment;
import com.example.assignment2.R;
import com.example.assignment2.databinding.ActivityInnerAppBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class InnerAppActivity extends AppCompatActivity {

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeItem);

        replaceFragment(new Home());

        // Set listeners of tapping the navBar items:
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();  // Instead of CASES

            if (itemId == R.id.homeItem) {
                replaceFragment(new Home());
            } else if (itemId == R.id.clubsItem) {
                replaceFragment(new Clubs());
            } else if (itemId == R.id.accountItem) {
                replaceFragment(new AccountFragment());
            }

            return true;

        });

    }




    public void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    public void selectHome() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.homeItem);

    }

    public void goToMainActivity() {
        Intent intent = new Intent(InnerAppActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showSongRequestDialog(String name, String clubName) {
        // Check if the activity is attached to a context
        if (this == null) {
            return;
        }

        // Show the dialog using the activity's context
        SongRequestDialogFragment.showDialog(this, name, clubName);
    }
}