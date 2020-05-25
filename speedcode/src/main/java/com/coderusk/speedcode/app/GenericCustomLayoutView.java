package com.coderusk.speedcode.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.vxplore.bikrimart_partner.interfaces.OnEventListener;

public abstract class GenericCustomLayoutView extends RelativeLayout {
    private static String STATE_SUPER_CLASS = "SuperClass";
    Context context=null;
    protected OnEventListener listener = null;
    public void setListener(OnEventListener listener) {
        this.listener = listener;
    }
//////////////////////////////////////////////////////
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
    }//dont touch


    /////////////////////////////////////////////////////////////
    //////////costructors///////////////////////////
    public GenericCustomLayoutView(Context context) {
        super(context);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    public GenericCustomLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    public GenericCustomLayoutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    protected abstract void findViews();

    private void initialize()
    {
        findViews();
        setViewActions();
    }

    protected abstract void setViewActions();

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getLayoutId(), this);
    }//dont touch

    protected abstract int getLayoutId();

    public abstract void setData(Object object);

}
