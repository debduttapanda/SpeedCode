package com.coderusk.speedcode.app;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coderusk.speedcode.R;


public class AlertOkDialog extends PandaDialog {
    Button bt_ok;
    TextView tv_text;
    String message;

    public interface OnActionListener
    {
        void onOk();
    }

    private OnActionListener listener = null;
    public AlertOkDialog(Context context, String title, String message, OnActionListener listener) {
        super(context, title);
        this.listener = listener;
        this.layout = R.layout.alert_ok_dialog_layout;
        this.cancelable = false;
        this.dimBehind = false;
        this.swidth = "-2";
        this.sheight = "-2";
        this.message = message;
    }

    public String getMessage()
    {
        return tv_text.getText().toString();
    }
    public void setMessage(String message)
    {
        tv_text.setText(message);
    }

    @Override
    protected void initializeUi() {
        super.initializeUi();
        bt_ok = dialog.findViewById(R.id.bt_ok);
        tv_text = dialog.findViewById(R.id.tv_text);
        tv_text.setText(message);
        ////////////
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOk();
            }
        });

    }

    private void onOk() {
        if(listener!=null)
        {
            listener.onOk();
        }
        dialog.dismiss();
    }
}
