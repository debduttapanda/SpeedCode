package com.coderusk.speedcode.app;

import android.os.Build;

public class Device {
    public static String getDeviceInformation()
    {
        String device = "";
        StringBuilder sb = new StringBuilder();
        sb.append("SERIAL: " + Build.SERIAL+"\n");
        sb.append("MODEL: " + Build.MODEL+"\n");
        sb.append("ID: " + Build.ID+"\n");
        sb.append("MANUFACTURER: " + Build.MANUFACTURER+"\n");
        sb.append("BRAND: " + Build.BRAND+"\n");
        sb.append("TYPE: " + Build.TYPE+"\n");
        sb.append("USER: " + Build.USER+"\n");
        sb.append("BASE: " + Build.VERSION_CODES.BASE+"\n");
        sb.append("INCREMENTAL " + Build.VERSION.INCREMENTAL+"\n");
        sb.append("SDK  " + Build.VERSION.SDK_INT+"\n");
        sb.append("BOARD: " + Build.BOARD+"\n");
        sb.append("BRAND " + Build.BRAND+"\n");
        sb.append("HOST " + Build.HOST+"\n");
        sb.append("FINGERPRINT: "+Build.FINGERPRINT+"\n");
        sb.append("VERSION CODE: " + Build.VERSION.RELEASE+"\n");
        device = sb.toString();
        return device;
    }
}
