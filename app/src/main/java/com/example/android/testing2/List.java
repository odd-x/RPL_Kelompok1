package com.example.android.testing2;

import android.media.Image;
import android.widget.ImageView;

public class List {

    private String title;
    private String seat;
    private String code;
    private int logo;

    public List() {
        //empty

    }

    public List(String title, String seat, String code, int logo) {
        this.title = title;
        this.seat = seat;
        this.code = code;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public String getSeat() {
        return seat;
    }

    public String getCode() {
        return code;
    }

    public int getLogo() {
        return logo;
    }
}
