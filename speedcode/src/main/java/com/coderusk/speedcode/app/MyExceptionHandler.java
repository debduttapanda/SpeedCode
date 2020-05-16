package com.coderusk.speedcode.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    protected Class afterCrashStartActiviry = null;
    private Context context;
    private Activity activity;
    int intentFlag=Intent.FLAG_ACTIVITY_CLEAR_TOP
            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            | Intent.FLAG_ACTIVITY_NEW_TASK;

    public MyExceptionHandler(Activity a) {
        //context = CustomApplication.getInstance().getBaseContext();
        context = a.getApplication().getBaseContext();
        activity = a;
    }

    public Class getAfterCrashStartActiviry() {
        return afterCrashStartActiviry;
    }

    public void setAfterCrashStartActiviry(Class afterCrashStartActiviry) {
        this.afterCrashStartActiviry = afterCrashStartActiviry;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(afterCrashStartActiviry==null)
        {
            return;
        }
        try {
            Intent intent = new Intent(context, afterCrashStartActiviry);
            intent.putExtra("crash", "crash");
            intent.addFlags(intentFlag);
            StringBuilder logsb =LogsUtil.readLogs();
            String logs = "";
            if(logsb!=null)
            {
                logs = logsb.toString();
            }
            logs = "Log_Data:\n" + logs;
            intent.putExtra("error", logs+"\n\n\n\n\n\n"+activity.getLocalClassName()+"-"+Log.getStackTraceString(ex));
            //Toast.makeText(context,"Something went wrong!\nDon't worry we are trying to detect.",Toast.LENGTH_SHORT).show();
            activity.startActivity(intent);
            //activity.finish();

            System.exit(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}