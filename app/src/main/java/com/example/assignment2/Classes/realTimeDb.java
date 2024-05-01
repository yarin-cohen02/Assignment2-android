package com.example.assignment2.Classes;

import static android.content.ContentValues.TAG;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class realTimeDb {

private static Map<Integer, Timer> timers = new HashMap<>();

    public static void fetchRandomSongFromFirebase(ArrayList<DjModel> djs, AA_RecyclerViewAdapter adapter) {
        DatabaseReference songsRef = FirebaseDatabase.getInstance().getReference("data/songs");

        songsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get list of songs
                List<Song> songs = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Song song = snapshot.getValue(Song.class);
                    songs.add(song);
                }

                // Update DjModel's nowPlaying property for each item in RecyclerView
                for (int i = 0; i < djs.size(); i++) {
                    final int finalI = i; // Create final variable to hold the value of i
                    DjModel djModel = djs.get(i);
                    Song song = songs.get(i % songs.size()); // Ensure we loop through songs

                    // Set nowPlaying property initially
                    djModel.setNowPlaying(song.getTitle());

                    // Schedule periodic updates for nowPlaying property with a new TimerTask for each DjModel
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Random random = new Random();
                                    Song randomSong = songs.get(random.nextInt(songs.size()));
                                    djModel.setNowPlaying(randomSong.getTitle()); // Use finalI here
                                    adapter.notifyItemChanged(finalI); // Use finalI here
                                }
                            });
                        }
                    }, 0, song.getDurationInMillis()); // Change to milliseconds


                    // Store timer reference to cancel it later if needed
                    timers.put(i, timer);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error fetching songs from Firebase: " + databaseError.getMessage());
            }
        });
    }

    public static void fetchCommercialsFromFirebase( AA_RecyclerViewAdapter.AdViewHolder adViewHolder) {
        DatabaseReference commercialsRef = FirebaseDatabase.getInstance().getReference("data/commercials");

        commercialsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Commercial> commercials = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Commercial commercial = snapshot.getValue(Commercial.class);
                    commercials.add(commercial);
                }

                if (!commercials.isEmpty()) {
                    Commercial randomCommercial = commercials.get(new Random().nextInt(commercials.size()));
                    adViewHolder.bind(randomCommercial); // Bind random commercial to the view holder
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error fetching commercials from Firebase: " + databaseError.getMessage());
            }
        });
    }


        public static void cancelAllTimers() {
        // Cancel all timers
        for (Timer timer : timers.values()) {
            timer.cancel();
        }
        timers.clear();
    }


}
