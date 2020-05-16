package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Bitmap;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

public class Piku {

    public interface PikListener
    {
        void onBitmap(Bitmap bitmap);
    }
    public static void pik(Context context,PikListener listener)
    {
        if(!(context instanceof BaseActivity)){return;}
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        if(listener!=null)
                        {
                            listener.onBitmap(r.getBitmap());
                        }
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        //TODO: do what you have to if user clicked cancel
                    }
                }).show(((BaseActivity)context).getSupportFragmentManager());
    }
}
