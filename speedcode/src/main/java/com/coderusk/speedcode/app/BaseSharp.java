package com.coderusk.speedcode.app;

import android.content.Context;
import android.content.SharedPreferences;

public class BaseSharp {
    protected static String PREFS_NAME = "SHARED_PREFERENCE";
    private SharedPreferences sharedPreferences;
    private Context context;
    ///////////////////////////////
    public void clear()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
    ///////////////////////////////
    public static String getPrefsName() {
        return PREFS_NAME;
    }

    public static void setPrefsName(String prefsName) {
        PREFS_NAME = prefsName;
    }
    ////////////////////////////////

    public BaseSharp(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    }
    //////////////////////////////////

    public String getString(String key)
    {
        return sharedPreferences.getString(key,"");
    }

    public void setString(String key, String value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public int getInt(String key)
    {
        return sharedPreferences.getInt(key,0);
    }

    public void setInt(String key, int value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean getBool(String key)
    {
        return sharedPreferences.getBoolean(key,false);
    }

    public void setBool(String key, boolean value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public float getFloat(String key)
    {
        return sharedPreferences.getFloat(key,0f);
    }

    public void setFloat(String key, float value)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
    //////////////////////////////////
}