package com.coderusk.speedcode.app;

import android.content.Context;
import android.os.Handler;

public class Worker {
    private Context context;

    public interface OnScheduleTouchedListener
    {
        void onScheduleTouched();
    }

    private OnScheduleTouchedListener onScheduleTouchedListener = null;

    public Worker(Context context) {
        this.context = context;
    }

    public void scheduleAfter(int millis, OnScheduleTouchedListener listener)
    {
        this.onScheduleTouchedListener = listener;
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(onScheduleTouchedListener!=null)
            {
                onScheduleTouchedListener.onScheduleTouched();
            }
        }, millis);
    }
}
