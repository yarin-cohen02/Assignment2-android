package com.example.assignment2.Classes;

public class Song {

    private String title;
    private int duration;

    public Song() {
    }


    public Song(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getDurationInMillis() {
        return  this.duration*1000;
    }
}
