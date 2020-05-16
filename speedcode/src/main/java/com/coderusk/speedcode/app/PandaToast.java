package com.coderusk.speedcode.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coderusk.speedcode.R;


public class PandaToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    private PandaToast(Context context, String msg, int length, int bg, int icon) {
        super(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.et_toast_layout, (ViewGroup) ((Activity)context).findViewById(R.id.custom_toast_container));
        TextView tv = (TextView) layout.findViewById(R.id.txtvw);
        ImageView iv_icon = layout.findViewById(R.id.iv_icon);
        iv_icon.setImageDrawable(context.getResources().getDrawable(icon));
        RelativeLayout rl_container = (RelativeLayout) layout.findViewById(R.id.custom_toast_container);
        rl_container.setBackgroundResource(bg);
        tv.setText(msg);
        Toast toast = new Toast(context);
        toast.setDuration(length);
        toast.setView(layout);
        toast.show();
    }

    public static void showInformation(Context context, String msg, int length)
    {
        new PandaToast(context,msg,length,R.drawable.et_toast_background_normal,R.drawable.ic_information);
    }
    public static void showWarning(Context context, String msg, int length)
    {
        new PandaToast(context,msg,length,R.drawable.et_toast_background_warning,R.drawable.ic_warning);
    }
    public static void showError(Context context, String msg, int length)
    {
        new PandaToast(context,msg,length,R.drawable.et_toast_background_error,R.drawable.ic_cancel);
    }
    public static void showGood(Context context, String msg, int length)
    {
        new PandaToast(context,msg,length,R.drawable.et_toast_background_good,R.drawable.ic_tick_circled);
    }
}
