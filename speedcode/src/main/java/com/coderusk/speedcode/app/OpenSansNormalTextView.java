package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class OpenSansNormalTextView extends AppCompatTextView {

    public OpenSansNormalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansNormalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansNormalTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-Regular.ttf");
        setTypeface(tf , Typeface.NORMAL);

    }
}
