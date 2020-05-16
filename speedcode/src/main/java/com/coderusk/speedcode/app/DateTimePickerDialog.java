package com.coderusk.speedcode.app;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.coderusk.speedcode.R;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimePickerDialog extends PandaDialog {
    Button bt_ok;
    Button bt_cancel;
    DatePicker datePicker;
    TimePicker timePicker;
    TabLayout tabs;
    private OnActionListener listener = null;


    public DateTimePickerDialog(Context context, String title, OnActionListener listener) {
        super(context, title);
        this.listener = listener;
        this.layout = R.layout.date_time_picker_dialog_layout;
        this.cancelable = false;
        this.dimBehind = false;
        this.swidth = "-2";
        this.sheight = "-2";
    }

    @Override
    protected void initializeUi() {
        super.initializeUi();
        tabs = dialog.findViewById(R.id.tabs);
        datePicker = dialog.findViewById(R.id.date);
        timePicker = dialog.findViewById(R.id.time);
        bt_ok = dialog.findViewById(R.id.bt_ok);
        bt_cancel = dialog.findViewById(R.id.bt_cancel);
        ///////////
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

            }
        });
        ////////////
        bt_ok.setOnClickListener(v -> onOk());
        bt_cancel.setOnClickListener(v -> onCancel());
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                switch (index)
                {
                    case 0:
                        adjustForDate();
                        break;
                    case 1:
                        adjustForTime();
                        break;
                    default:break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void adjustForDate() {
        datePicker.setVisibility(View.VISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
    }

    private Calendar getCalendar() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

        int hour = 0;
        int mint = 0;
        if(Build.VERSION.SDK_INT < 23){
            hour = timePicker.getCurrentHour();
            mint = timePicker.getCurrentMinute();
        } else{
            hour = timePicker.getHour();
            mint = timePicker.getMinute();
        }

        String stamp = String.format(Locale.ENGLISH,"%02d-%02d-%04d %02d:%02d",day,month,year,hour,mint);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(stamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }

    private void adjustForTime() {
        datePicker.setVisibility(View.GONE);
        timePicker.setVisibility(View.VISIBLE);
    }

    private void onCancel() {
        if (listener != null) {
            listener.onCancel();
        }
        dialog.dismiss();
    }

    private void onOk() {
        if (listener != null) {
            listener.onOk(getCalendar());
        }
        dialog.dismiss();
    }

    public interface OnActionListener {
        void onOk(Calendar calendar);

        void onCancel();
    }
}