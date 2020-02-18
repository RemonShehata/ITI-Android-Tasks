package com.example.listview;

import androidx.annotation.NonNull;

public class Number {
    private String title;
    private String desc;
    private int img;

    public Number(String title, String desc, int img) {
        this.title = title;
        this.desc = desc;
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
