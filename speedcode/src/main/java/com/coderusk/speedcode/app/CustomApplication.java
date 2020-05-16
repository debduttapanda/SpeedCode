
package com.coderusk.speedcode.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.multidex.MultiDex;

public class CustomApplication extends Application {

    DataBank dataBank;
    @Override
    public void onCreate() {
        super.onCreate();
        setAppCrashRestartActivity();
        initApplication();
        dataBank = new DataBank();
    }

    public void setAppCrashRestartActivity()
    {

    }

    private void initApplication()
    {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(activity));
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    public DataBank getDataBank()
    {
        return dataBank;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
