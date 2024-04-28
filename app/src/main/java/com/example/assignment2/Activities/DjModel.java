package com.example.assignment2.Activities;

public class DjModel {

    String djName;
    String djStyle;
    String djClub;
    String nowPlaying;
    int djImg;

    public DjModel(String djName, String djStyle, String djClub, String nowPlaying, int djImg) {
        this.djName = djName;
        this.djStyle = djStyle;
        this.djClub = djClub;
        this.nowPlaying = nowPlaying;
        this.djImg = djImg;
    }

    public DjModel() {
    }

    public String getDjName() {
        return djName;
    }

    public String getDjStyle() {
        return djStyle;
    }

    public String getDjClub() {
        return djClub;
    }

    public String getNowPlaying() {
        return nowPlaying;
    }

    public int getDjImg() {
        return djImg;
    }
}
