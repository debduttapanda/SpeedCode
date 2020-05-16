package com.coderusk.speedcode.app;

import androidx.annotation.DrawableRes;

public class SelectionItem {
    private int id = 0;
    String text = "";
    @DrawableRes int drawable = 0;

    public SelectionItem(int id, String text, int drawable) {
        this.id = id;
        this.text = text;
        this.drawable = drawable;
    }

    public SelectionItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
