package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class FontedTextView extends AppCompatTextView {

    public FontedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontedTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/timeburner.TTF");
        setTypeface(tf , Typeface.NORMAL);

    }
}