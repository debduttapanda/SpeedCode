package com.coderusk.speedcode.app;

import android.util.Log;

public class Loger {
    public static String TAG = "LOGER";

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        Loger.TAG = TAG;
    }

    public static void log(String msg) {
        try {
            int level = 3;
            StackTraceElement[] traces;
            traces = Thread.currentThread().getStackTrace();
            String display = ("\n"+msg+" near "  + traces[level] + " " );
            Log.d(TAG,display);
        } catch (Exception e) {
            e.printStackTrace();
            String display = msg +" near Undetected location";
            Log.d(TAG,display);
        }
    }
}
