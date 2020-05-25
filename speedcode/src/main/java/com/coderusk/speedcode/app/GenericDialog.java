package com.coderusk.speedcode.app;

import android.content.Context;

public abstract class GenericDialog extends PandaDialog {
    protected OnEventListener listener = null;
    protected GenericDialog(Context context, String title, OnEventListener listener) {
        super(context, title);
        this.listener = listener;
        this.layout = getLayoutId();
        this.cancelable = false;
        this.dimBehind = false;
        this.swidth = "-2";
        this.sheight = "-2";
    }

    protected abstract int getLayoutId();


    @Override
    protected void initializeUi() {
        super.initializeUi();
        findViews();
        setViewData();
        setViewActions();
    }

    protected abstract Object getOnOkData();

    protected abstract Object getOnCancelData();

    protected abstract void findViews();

    protected abstract void setViewActions();
    protected abstract void setViewData();
}
