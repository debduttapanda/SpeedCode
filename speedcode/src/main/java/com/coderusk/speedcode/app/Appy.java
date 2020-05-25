package com.coderusk.speedcode.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.coderusk.speedcode.BuildConfig;

public class Appy {
    private Context context;
    private static Class splashActivity = null;

    public Class getSplashActivity() {
        return splashActivity;
    }

    public void setSplashActivity(Class splashActivity) {
        this.splashActivity = splashActivity;
    }

    private Appy(Context context) {
        this.context = context;
    }

    public static Appy with(Context context)
    {
        return new Appy(context);
    }

    public int getVersion()
    {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final int versionCode = packageInfo.versionCode;
        Log.d("version_code",versionCode+"");
        return versionCode;
    }

    public void updateFromStore()
    {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }



    public void exit()
    {
        if(context instanceof BaseActivity)
        {
            ((BaseActivity)context).finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void logout() {
        ConfirmationDialog dialog = new ConfirmationDialog(context, "Logout", "Are you sure to logout?", new ConfirmationDialog.OnActionListener() {
            @Override
            public void onOk() {
                BaseSharp sharp = new BaseSharp(context);
                sharp.clear();
                Navi navi = new Navi(context);
                navi.target(splashActivity).go();
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.show();
    }

    public void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Bikrimart");
            String shareMessage= "\nBikrimart is a nice Application.\nGrocery and more is just a click away.\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.LIBRARY_PACKAGE_NAME +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "Share by:"));
        } catch(Exception e) {
            //e.toString();
        }
    }
}
