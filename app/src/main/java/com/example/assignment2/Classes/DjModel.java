package com.example.assignment2.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class DjModel implements Parcelable {

    private String djName;
    private String djStyle;
    private String djClub;
    private String nowPlaying;
    private int djImg;

    public DjModel(String djName, String djStyle, String djClub, String nowPlaying, int djImg) {
        this.djName = djName;
        this.djStyle = djStyle;
        this.djClub = djClub;
        this.nowPlaying = nowPlaying;
        this.djImg = djImg;
    }

    protected DjModel(Parcel in) {
        djName = in.readString();
        djStyle = in.readString();
        djClub = in.readString();
        nowPlaying = in.readString();
        djImg = in.readInt();
    }

    public static final Creator<DjModel> CREATOR = new Creator<DjModel>() {
        @Override
        public DjModel createFromParcel(Parcel in) {
            return new DjModel(in);
        }

        @Override
        public DjModel[] newArray(int size) {
            return new DjModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(djName);
        dest.writeString(djStyle);
        dest.writeString(djClub);
        dest.writeString(nowPlaying);
        dest.writeInt(djImg);
    }
}
