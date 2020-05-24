package com.coderusk.speedcode.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderusk.speedcode.R;

public class ParamView extends RelativeLayout {
    private static String STATE_SUPER_CLASS = "SuperClass";
    Context context = null;

    private TextView tv_title, tv_value;

    public ParamView(Context context) {
        super(context);
        this.context = context;
        initializeViews(context);
        initialize();
    }

    public ParamView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeViews(context);
        initialize();
    }


    public ParamView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeViews(context);
        initialize();
    }

    private void setAllDataToBundle(Bundle bundle) {

    }

    private void getAllDataFromBundle(Bundle bundle) {
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();

        bundle.putParcelable(STATE_SUPER_CLASS, super.onSaveInstanceState());
        setAllDataToBundle(bundle);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;

            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER_CLASS));
            getAllDataFromBundle(bundle);
            //updateUIFromData();
        } else
            super.onRestoreInstanceState(state);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchSaveInstanceState(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchRestoreInstanceState(container);
    }//dont touch

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //add custom functionalities
    }

    private void initialize() {
        tv_title = findViewById(R.id.tv_title);
        tv_value = findViewById(R.id.tv_value);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.param_view_layout, this);
    }

    public void setValue(ParamData paramData) {
        tv_title.setText(paramData.getTitle());
        tv_value.setText(paramData.getValue());
    }
}