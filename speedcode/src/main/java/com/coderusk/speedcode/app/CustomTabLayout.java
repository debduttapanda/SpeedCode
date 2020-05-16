package com.coderusk.speedcode.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class CustomTabLayout extends TabLayout {
    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        try {
            if (getTabCount() == 0)
            {
                return;
            }
            int fw = getMeasuredWidth();
            int w = 0;
            int count = getTabCount();
            for(int i=0;i<count;++i)
            {
                Tab tab = getTabAt(i);
                View view = tab.view;
                int tw = view.getMeasuredWidth();
                w += tw;
            }
            if(w<=fw)
            {
                setTabMode(MODE_FIXED);
            }
            else
            {
                setTabMode(MODE_SCROLLABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
