package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class HyperlinkView extends AppCompatTextView {
    private Context context = null;
    private void constructor(Context context)
    {
        this.context = context;
    }
    public HyperlinkView(Context context) {
        super(context);
        constructor(context);
    }

    public HyperlinkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor(context);
    }

    public HyperlinkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }

    protected void onDraw (Canvas canvas) {
        setPaintFlags(getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        super.onDraw(canvas);


    }


}
