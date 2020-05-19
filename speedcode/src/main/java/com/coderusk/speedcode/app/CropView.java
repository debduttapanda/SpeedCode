package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.IntRange;

import com.coderusk.speedcode.R;
import com.coderusk.speedcode.app.cropper.CropImageView;
import com.coderusk.speedcode.app.cropper.callback.CropCallback;
import com.coderusk.speedcode.app.cropper.callback.LoadCallback;

import java.io.File;

import static com.coderusk.speedcode.app.cropper.CropImageView.CropMode.FREE;


public class CropView extends RelativeLayout {
    private static String STATE_SUPER_CLASS = "SuperClass";
    Context context = null;

    CropImageView mCropView;
    ImageView iv_clock;
    ImageView iv_anticlock;
    ImageView iv_done;
    ImageView iv_crop_back;
    Bitmap bitmap = null;
    View whitener, blacker;
    private int quality = 100;


    //////////costructors///////////////////////////
    public CropView(Context context) {
        super(context);
        this.context = context;
        initializeViews(context);
        initialize();
    }


    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeViews(context);
        initialize();
    }

    public CropView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeViews(context);
        initialize();
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
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //add custom functionalities
    }

    private void initialize() {
        findViews();
        setViewActions();
    }

    public interface OnEventListener
    {
        void onBitmap(Bitmap bitmap);
        void onError(Throwable e);
    }

    private OnEventListener listener = null;

    public void setOnActionListener(OnEventListener listener)
    {
        this.listener = listener;
    }

    private void defaultConfigure()
    {
        mCropView.setCropMode(FREE);
        mCropView.setMaxRect(300,300);
        mCropView.setCompressFormat(Bitmap.CompressFormat.JPEG);
        mCropView.setCompressQuality(50);
    }

    private void setViewActions() {
        setOnClickListener(null);
        iv_crop_back.setOnClickListener(v -> setVisibility(GONE));
        iv_clock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap!=null)
                {
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                }
            }
        });
        iv_anticlock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap!=null)
                mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
            }
        });
        iv_done.setOnClickListener(v -> onDoneClick());
        //////////////////////////////
        defaultConfigure();
        ///////////////////////////////
        whitener.setOnClickListener(v -> setWhite());
        blacker.setOnClickListener(v -> setBlack());
    }

    private void setWhite() {
        mCropView.setBackgroundColor(Color.WHITE);
    }

    private void setBlack() {
        mCropView.setBackgroundColor(Color.BLACK);
    }

    private void onDoneClick() {
        if(bitmap==null){return;}
        ////////////////////////////////////////////////
        ((BaseActivity)(context)).startWait();
        mCropView.crop(null).execute(new CropCallback() {
            @Override
            public void onSuccess(Bitmap cropped) {
                ((BaseActivity)(context)).stopWait();
                setVisibility(GONE);
                if(listener!=null)
                {
                    listener.onBitmap(cropped);
                }
            }

            @Override
            public void onError(Throwable e) {
                ((BaseActivity)(context)).stopWait();
                ((BaseActivity)(context)).error("Something went wrong:"+e.getMessage());
                if(listener!=null)
                {
                    listener.onError(e);
                }
            }
        });
    }

    private void findViews() {
        whitener = findViewById(R.id.whitener);
        blacker = findViewById(R.id.blacker);
        mCropView = findViewById(R.id.cropview);
        iv_clock = findViewById(R.id.iv_clock);
        iv_anticlock = findViewById(R.id.iv_anticlock);
        iv_done = findViewById(R.id.iv_done);
        iv_crop_back = findViewById(R.id.iv_crop_back);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.crop_view_layout, this);
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
        load();
    }

    public void configure(
            @IntRange(from = 0,to = 1920) int height,
            @IntRange(from = 0,to = 1920) int width,
            CropImageView.CropMode mode,
            Bitmap.CompressFormat compressFormat,
            @IntRange(from = 0,to = 100) int quality
    )
    {
        if(height==0)
        {
            mCropView.setOutputWidth(width);
        }
        else
        {
            mCropView.setMaxRect(width,height);
        }

        mCropView.setCropMode(mode);
        mCropView.setCompressFormat(compressFormat);
        mCropView.setCompressQuality(quality);
        this.quality = quality;
    }

    private Uri bitmapToUri(Bitmap bitmap, String name)
    {
        File f = FileManager.saveBitmapAsJpeg(context,bitmap,name);
        return Uri.fromFile(f);
    }

    private void load()
    {
        setVisibility(VISIBLE);
        mCropView.load(bitmapToUri(bitmap,"temp")).execute(new LoadCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
