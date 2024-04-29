package com.example.assignment2.Activities;

import static android.content.ContentValues.TAG;

import com.example.assignment2.Classes.AA_RecyclerViewAdapter;
import com.example.assignment2.Classes.DjModel;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.assignment2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        RecyclerView recyclerView = findViewById(R.id.myRecycler);
//
//        ArrayList<DjModel> djs = new ArrayList<>(Arrays.asList(
//
//                new DjModel("בן אזולאי","פופ","DramaTLV","Avicii - The Nights",R.drawable.images),
//                new DjModel("סקאזי","רוק","BlueMoon","Amnezia - ABCD",R.drawable.images),
//                new DjModel("איתי גאלו","פופ","PortTLV","נועה קירל - שלושה בנות",R.drawable.images)
//        ));
//
//        AA_RecyclerViewAdapter adapter = new AA_RecyclerViewAdapter(this, djs);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void regFunc(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });

    }


    public void loginFunc(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmail:FAIL");
                        }
                    }
                });
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }


}