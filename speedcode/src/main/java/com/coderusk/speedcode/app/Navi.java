package com.coderusk.speedcode.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.coderusk.speedcode.R;

public class Navi {
    Intent intent = null;
    boolean finish = true;
    private Context context;

    public Navi(Context context) {
        this.context = context;
    }

    public Navi target(Class activity) {
        intent = new Intent(context, activity);
        return this;
    }

    public Navi add(String key, String value) {
        if (intent != null) {
            intent.putExtra(key, value);
            return this;
        } else {
            return null;
        }
    }

    public Navi add(String key, Parcelable value) {
        if (intent != null) {
            intent.putExtra(key, value);
            return this;
        } else {
            return null;
        }
    }

    public Navi finish(boolean yes) {
        if (intent != null) {
            finish = yes;
            return this;
        } else {
            return null;
        }
    }

    public void go() {
        if (intent != null) {
            context.startActivity(intent);
            ((BaseActivity) context).overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            if (finish) {
                ((BaseActivity) context).finish();
            }
            intent = null;
            finish = true;
        }
    }
    public void back() {
        if (intent != null) {
            context.startActivity(intent);
            ((BaseActivity) context).overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right );
            if (finish) {
                ((BaseActivity) context).finish();
            }
            intent = null;
            finish = true;
        }
        else
        {
            ((BaseActivity) context).finish();
            ((BaseActivity) context).overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
        }
    }


    public void shareGo(Pair<View, String>... sharedElements)
    {
        if (intent != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(((BaseActivity) context),sharedElements);

                ActivityCompat.startActivity(context, intent, options.toBundle());
                intent = null;
                finish = true;
            } else{
                go();
            }
        }
    }

    public void shareBack()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            ((BaseActivity) context).finishAfterTransition();
        } else{
            ((BaseActivity) context).finish();
        }
    }

    public Navi add(String key, boolean value) {
        if (intent != null) {
            intent.putExtra(key, value);
            return this;
        } else {
            return null;
        }
    }
}
