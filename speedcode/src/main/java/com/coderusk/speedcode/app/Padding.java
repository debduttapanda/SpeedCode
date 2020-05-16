package com.coderusk.speedcode.app;

import android.view.View;

public class Padding
{
    int left;
    int right;
    int top;
    int bottom;

    public Padding(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public Padding(View v) {
        left = v.getPaddingLeft();
        top = v.getPaddingTop();
        right = v.getPaddingRight();
        bottom = v.getPaddingBottom();
    }

    public void set(View v)
    {
        v.setPadding(left,top,right,bottom);
    }
}
