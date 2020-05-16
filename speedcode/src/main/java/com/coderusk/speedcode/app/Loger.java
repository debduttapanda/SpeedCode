package com.coderusk.speedcode.app;

import android.util.Log;

public class Loger {
    public static void log(String msg) {
        try {
            int level = 3;
            StackTraceElement[] traces;
            traces = Thread.currentThread().getStackTrace();
            String display = (msg+" near "  + traces[level] + " " );
            Log.d("BIKRIMART_PARTNER_LOGER",display);
        } catch (Exception e) {
            e.printStackTrace();
            String display = msg +" near Undetected location";
            Log.d("BIKRIMART_PARTNER_LOGER",display);
        }
    }
}
