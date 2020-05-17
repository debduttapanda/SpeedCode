package com.coderusk.speedcode.app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.coderusk.speedcode.R;

public class ProgressiveNextView extends RelativeLayout {
    private static String STATE_SUPER_CLASS = "SuperClass";
    Context context=null;

    private ProgressBar progressBar;
    private ImageView iv_next;
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
    public ProgressiveNextView(Context context) {
        super(context);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    public ProgressiveNextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    public ProgressiveNextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        initializeViews(context);
        initialize();
    }

    private void initialize()
    {
        progressBar = findViewById(R.id.pb_progress);
        iv_next = findViewById(R.id.iv_next);
        iv_next.setEnabled(false);
        progressBar.setProgress(0);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.progressive_next_view_layout, this);
    }//dont touch

    ValueAnimator va = null;

    public void setAction(OnClickListener listener)
    {
        iv_next.setOnClickListener(listener);
    }

    public void setProgress(int progress)
    {
        if(va!=null)
        {
            va.cancel();
        }
        int current = progressBar.getProgress();
        va = ValueAnimator.ofInt(current, progress);
        int mDuration = 300;
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progressValue = (int)animation.getAnimatedValue();
                progressBar.setProgress(progressValue);
                if(progressValue==100)
                {
                    iv_next.setEnabled(true);
                }
                else
                {
                    iv_next.setEnabled(false);
                }
            }
        });
        va.start();
    }

}
