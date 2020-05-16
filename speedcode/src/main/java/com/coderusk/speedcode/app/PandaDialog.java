package com.coderusk.speedcode.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.coderusk.speedcode.R;


public abstract class PandaDialog {
    /////////////////////////
    protected TextView tv_head;
    ////////////////////////
    protected int layout = R.layout.fancy_dialog_layout;
    protected Context context;
    protected Dialog dialog = null;
    /////////////////////////
    protected String title = "SearchableListDialog";
    protected boolean cancelable = true;
    protected boolean transparentBackground = true;
    protected boolean dimBehind = false;
    protected String swidth = "80%";
    protected String sheight = "80%";

    public PandaDialog(Context context, String title) {
        this.context = context;
        this.title = title;
    }

    protected void initializeUi() {
        tv_head = dialog.findViewById(R.id.tv_head);
    }

    private void configureDialog(
            boolean cancelable,
            int layout,
            boolean transparentBackground,
            boolean dimBehind,
            String swidth,
            String sheight) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancelable);
        dialog.setContentView(layout);
        /////////////////////
        initializeUi();
        tv_head.setText(title);
        ///////////////////
        if (transparentBackground) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        if (!dimBehind) {
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

            window.setAttributes(wlp);
        }
        Size size = parseSize(swidth, sheight);
        if(swidth=="-1"){size.width = -1;}
        if(swidth=="-2"){size.width = -2;}
        if(sheight=="-1"){size.height = -1;}
        if(sheight=="-2"){size.height = -2;}
        dialog.getWindow().setLayout(size.getWidth(), size.getHeight());

    }

    private Size parseSize(String swidth, String sheight) {
        return new Size(parseDimension(swidth), parseDimension(sheight));
    }

    private int parseDimension(String measure) {
        if (measure == null) {
            return 0;
        }
        if (measure.isEmpty()) {
            return 0;
        }
        if (measure.endsWith("px")) {
            measure = measure.replace("px", "");
            int ret = 0;
            try {
                ret = Integer.valueOf(measure);
                return ret;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        if (measure.endsWith("dp")) {
            measure = measure.replace("dp", "");
            int ret = 0;
            try {
                ret = Integer.valueOf(measure);
                return dpToPx(ret);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        if (measure.endsWith("%")) {
            measure = measure.replace("%", "");
            int ret = 0;
            try {
                ret = Integer.valueOf(measure);
                return (int) (context.getResources().getDisplayMetrics().widthPixels * (((float) ret) / 100.0f));
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private Size fromPixel(int width, int height) {
        return new Size(width, height);
    }

    private Size fromDp(int width, int height) {
        return fromPixel(dpToPx(width), dpToPx(height));
    }

    private int dpToPx(float measure) {
        float dip = measure;
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
        return (int) px;
    }

    private Size fromPercent(int wp, int hp) {
        float wf = ((float) wp) / 100;
        float hf = ((float) hp) / 100;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * wf);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * hf);
        Size size = new Size(width, height);
        return size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isTransparentBackground() {
        return transparentBackground;
    }

    public void setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
    }

    public boolean isDimBehind() {
        return dimBehind;
    }

    public void setDimBehind(boolean dimBehind) {
        this.dimBehind = dimBehind;
    }

    public String getSwidth() {
        return swidth;
    }

    public void setSwidth(String swidth) {
        this.swidth = swidth;
    }

    public String getSheight() {
        return sheight;
    }

    public void setSheight(String sheight) {
        this.sheight = sheight;
    }

    public void configure(
            String title,
            boolean cancelable,
            boolean transparentBackground,
            boolean dimBehind,
            String swidth,
            String sheight) {
        this.title = title;
        this.cancelable = cancelable;
        this.transparentBackground = transparentBackground;
        this.dimBehind = dimBehind;
        this.swidth = swidth;
        this.sheight = sheight;
    }

    public void show() {
        dialog = new Dialog(context);
        configureDialog(cancelable, layout, transparentBackground, dimBehind, swidth, sheight);
        dialog.show();
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private class Size {
        int width = 0;
        int height = 0;

        public Size() {
        }

        public Size(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
