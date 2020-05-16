package com.coderusk.speedcode.app;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.coderusk.speedcode.R;

import java.util.ArrayList;

public class SearchableListDialogWhite {
    /////////////////////////
    FrameLayout fr_search;
    RecyclerView rv_values;
    AutoCompleteTextView at_search;
    ImageView iv_close;
    ImageView iv_ok;
    LottieAnimationView lav_search;
    TextView tv_head;
    ////////////////////////
    boolean search_state = false;
    int layout = R.layout.searchable_list_dialog_layout_white;
    private Context context;
    private ArrayList<String> values = null;
    private Dialog dialog = null;
    /////////////////////////
    private String title = "SearchableListDialog";
    private boolean cancelable = true;
    private boolean transparentBackground = true;
    private boolean dimBehind = false;
    private String swidth = "80%";
    private String sheight = "80%";

    public interface OnSelectedListener
    {
        void onSelect(int index, String value);
    }

    private OnSelectedListener listener = null;

    public SearchableListDialogWhite(String title, Context context, ArrayList<String> values, OnSelectedListener listener) {
        this.context = context;
        this.values = values;
        this.title = title;
        this.listener = listener;
    }

    private void initializeUi() {
        fr_search = dialog.findViewById(R.id.fr_search);
        rv_values = dialog.findViewById(R.id.rv_values);
        at_search = dialog.findViewById(R.id.at_search);
        iv_close = dialog.findViewById(R.id.iv_close);
        iv_ok = dialog.findViewById(R.id.iv_ok);
        lav_search = dialog.findViewById(R.id.lav_search);
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
        setupUiAction();
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

    public void configure(
            String title,
            boolean cancelable,
            boolean transparentBackground,
            boolean dimBehind,
            String swidth,
            String sheight) {
        this.values = values;
        this.title = title;
        this.cancelable = cancelable;
        this.transparentBackground = transparentBackground;
        this.dimBehind = dimBehind;
        this.swidth = swidth;
        this.sheight = sheight;
    }

    public void show() {
        if (values.size() < 1) {
            return;
        }
        dialog = new Dialog(context);
        configureDialog(cancelable, layout, transparentBackground, dimBehind, swidth, sheight);
        dialog.show();
    }

    private void onSelected(int index, String value) {
        if(listener!=null)
        {
            listener.onSelect(index,value);
        }
    }

    private void setupRecyclerList() {
        rv_values.setLayoutManager(new LinearLayoutManager(context));
        SearchableListAdapterWhite searchableListAdapter = new SearchableListAdapterWhite(context, values, new SearchableListAdapterWhite.OnSelectListener() {
            @Override
            public void onSelect(int index, String country) {
                onSelected(index, country);
                dialog.dismiss();
            }
        });
        rv_values.setAdapter(searchableListAdapter);
    }

    private void setupCloseButton() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                at_search.setText("");
            }
        });
    }

    private void setupOkButton() {
        iv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = at_search.getText().toString();
                int index = values.indexOf(country);
                if (values.contains(country)) {
                    onSelected(index, country);
                    dialog.dismiss();
                } else {
                    PandaToast.showError(context, "Error: Value not in the list.", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void setupAutoComplete() {
        if(Consty.DIALOG_OK_TICK)
        {
            at_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (at_search.getText().toString().isEmpty()) {
                        iv_ok.setVisibility(View.GONE);
                    } else {
                        iv_ok.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        else
        {
            at_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String country = at_search.getText().toString();
                    int index = values.indexOf(country);
                    if (values.contains(country)) {
                        onSelected(index, country);
                        dialog.dismiss();
                    } else {
                        PandaToast.showError(context, "Error: Value not in the list.", Toast.LENGTH_LONG);
                    }
                }
            });
        }
        AutoCompleteAdapter adapter = new AutoCompleteAdapter(context, values);
        at_search.setAdapter(adapter);
        //////////////////////////////

    }

    private void setupSearchButton() {
        lav_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonOnClick();
            }
        });
    }

    private void setupUiAction() {
        setupRecyclerList();
        setupCloseButton();
        setupOkButton();
        setupAutoComplete();
        setupSearchButton();
    }

    private void playSearchToClose() {
        lav_search.setSpeed(3f);
        lav_search.playAnimation();
    }

    private void playCloseToSearch() {
        lav_search.reverseAnimationSpeed();
        lav_search.setSpeed(-3f);
        lav_search.playAnimation();
    }

    private void actionForSearch() {
        fr_search.setVisibility(View.VISIBLE);
        at_search.requestFocus();
        showSoftKeyboard(at_search);
        iv_ok.setVisibility(at_search.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
    }

    private void actionForClose() {
        iv_ok.setVisibility(View.GONE);
        lav_search.requestFocus();
        hideSoftKeyboard(at_search);
        fr_search.setVisibility(View.GONE);
    }

    private void onSearchButtonOnClick() {
        search_state = !search_state;
        if (search_state) {
            actionForSearch();
            playSearchToClose();
        }
        if (!search_state) {
            actionForClose();
            playCloseToSearch();
        }
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
