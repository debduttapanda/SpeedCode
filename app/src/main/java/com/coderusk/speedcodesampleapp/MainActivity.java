package com.coderusk.speedcodesampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.coderusk.speedcode.app.BaseActivity;
import com.coderusk.speedcode.app.DateTimePickerDialog;

import java.util.Calendar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DateTimePickerDialog(this, "fsdfs", new DateTimePickerDialog.OnActionListener() {
            @Override
            public void onOk(Calendar calendar) {

            }

            @Override
            public void onCancel() {

            }
        }).show();
    }

    @Override
    public int getLoader_id() {
        return R.id.l_loader;
    }
}
