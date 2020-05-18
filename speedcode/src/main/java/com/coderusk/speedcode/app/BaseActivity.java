package com.coderusk.speedcode.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class BaseActivity extends AppCompatActivity {
    /**last modified = 06:05:2020 12:32**/
    public Integer abstractActivity = 1;
    protected Intent intent = null;
    LoaderView lav = null;
    protected boolean busy = false;
    //////////////////////////////////
    private long pressed = 0;

    public int getLoader_id() {
        return 0;
    }

    protected abstract void onCreateTasks();
    protected abstract void findViews();
    protected abstract void setViewsData();
    protected abstract void setViewActions();
    protected abstract void onStartTasks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        initialize();
        onSetContentView();
        onCreateActions();
    }

    protected abstract void onSetContentView();

    @Override
    protected void onStart() {
        super.onStart();
        onStartTasks();
    }

    protected void onCreateActions()
    {
        findViews();
        setViewsData();
        setViewActions();
        onCreateTasks();
    }

    public Navi navi()
    {
        return new Navi(this);
    }

    public void startWait() {
        busy = true;
        lav.setVisibility(View.VISIBLE);
        disable();
    }

    public void stopWait() {
        busy = false;
        lav.setVisibility(View.GONE);
        enable();
    }

    public void startNormalWait() {
        busy = true;
        lav.setVisibility(View.VISIBLE);
    }

    public void stopNormalWait() {
        busy = false;
        lav.setVisibility(View.GONE);
    }

    protected void disable() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    protected void enable() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        int id = getLoader_id();
        if(id!=0)
        {
            lav = findViewById(id);
        }
        else
        {
            lav = null;
        }


    }

    private void setTypeFace() {
        String font = "font/app_font1.ttf";
        Tyfo.overrideFont(this, "DEFAULT", font);
        Tyfo.overrideFont(this, "MONOSPACE", font);
        Tyfo.overrideFont(this, "SERIF", font);
        Tyfo.overrideFont(this, "SANS_SERIF", font);
    }
    ////////////////////////////

    private void initialize() {
        setTypeFace();
    }

    protected BaseSharp getSharp() {
        BaseSharp smp = new BaseSharp(this);
        return smp;
    }

    protected BaseWebs getWeber()
    {
        return new BaseWebs(this);
    }

    protected Worker getWorkManager() {
        return new Worker(this);
    }
    /////////////////////

    /////////////////////
    public void error(String message) {
        PandaToast.showError(this, message, Toast.LENGTH_SHORT);
    }

    protected void errorLong(String message) {
        PandaToast.showError(this, message, Toast.LENGTH_LONG);
    }

    protected void good(String message) {
        PandaToast.showGood(this, message, Toast.LENGTH_SHORT);
    }

    protected void goodLong(String message) {
        PandaToast.showGood(this, message, Toast.LENGTH_LONG);
    }

    public void warn(String message) {
        PandaToast.showWarning(this, message, Toast.LENGTH_SHORT);
    }

    protected void warnLong(String message) {
        PandaToast.showWarning(this, message, Toast.LENGTH_LONG);
    }

    protected void inform(String message) {
        PandaToast.showInformation(this, message, Toast.LENGTH_SHORT);
    }

    protected void informLong(String message) {
        PandaToast.showInformation(this, message, Toast.LENGTH_LONG);
    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        long dif = now - pressed;
        long sec = dif / 1000;
        pressed = now;
        if (Math.abs(sec) > 4) {
            PandaToast.showInformation(this, "Press back again to exit.", Toast.LENGTH_SHORT);
        } else {
            Appy.with(this).exit();
        }
    }

    protected String intentString(String key) {
        if (intent == null) {
            return "";
        }
        String value = intent.getStringExtra(key);
        if (value == null) {
            return "";
        }
        return value;
    }

    protected Parcelable intentParcel(String key) {
        if (intent == null) {
            return null;
        }
        return intent.getParcelableExtra(key);
    }

    /**
     * Call this when your activity is done and should be closed.  The
     * ActivityResult is propagated back to whoever launched you via
     * onActivityResult().
     */
    @Override
    public void finish() {
        Log.d("bug_activity",this.getClass().getName());
        super.finish();
    }
    ///////////////
    protected DataBank getDataBank()
    {
        return ((CustomApplication)getApplication()).getDataBank();
    }

    public <T> T objectify(String json, Class<T> classOfT){
        try {
            Gson gson = new Gson();
            Object object = gson.fromJson(json, classOfT);
            return classOfT.cast(object);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String unde(String input)
    {
        try {
            return URLEncoder.encode(input, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }


}
