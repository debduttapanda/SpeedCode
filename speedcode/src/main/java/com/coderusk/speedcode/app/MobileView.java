package com.coderusk.speedcode.app;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class MobileView extends AppCompatEditText {
    private Context context = null;
    private String prefix = "+91 ";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void constructor(Context context)
    {
        initialize();
    }

    private void initialize()
    {
        setupAutoPrefix();
        setInputType(InputType.TYPE_CLASS_PHONE);
    }

    private void setupAutoPrefix() {
        addTextChangedListener(new TextWatcher() {
            int count;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                count=i2;
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith(prefix) && count!=0){
                    s.insert(0, prefix);
                }
            }
        });
    }

    public MobileView(Context context) {
        super(context);
        constructor(context);
    }

    public MobileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor(context);
    }

    public MobileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context);
    }
}
