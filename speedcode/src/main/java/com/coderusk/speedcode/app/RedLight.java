package com.coderusk.speedcode.app;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.coderusk.speedcode.R;

public class RedLight {

    public RedLight(Context context) {
        this.context = context;
    }

    private Context context;
    public void validFocus(View v)
    {
        v.requestFocus();
        redLight((TextView) v);
    }

    private void redLight(TextView tv)
    {
        Padding padding = new Padding(tv);
        tv.setBackground(context.getResources().getDrawable(R.drawable.round_red_border_background));
        padding.set(tv);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    Padding padding = new Padding(tv);
                    tv.setBackground(context.getResources().getDrawable(R.drawable.round_gray_border_background));
                    //tv.removeTextChangedListener(this);
                    padding.set(tv);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Object tag = tv.getTag();
        if(tag!=null)
        {
            if(tag instanceof String)
            {
                if(!((String) tag).isEmpty())
                {
                    if(((String) tag).equalsIgnoreCase("red_light"))
                    {
                        return;
                    }
                }
            }
        }
        tv.addTextChangedListener(textWatcher);
        tv.setTag("red_light");
    }
}
