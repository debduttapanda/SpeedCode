package com.coderusk.speedcode.app;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coderusk.speedcode.R;

import java.util.ArrayList;

public class SortSelectionDialog extends PandaDialog {
    Button bt_cancel;
    TextView tv_text;
    RecyclerView rv_list;
    RadioGroup rg_dir;
    private ArrayList<SelectionItem> items = null;

    public interface OnActionListener
    {
        void onCancel();
        void onItemSelect(SelectionItem item, int dir);
    }

    private OnActionListener listener = null;
    public SortSelectionDialog(Context context, String title, ArrayList<SelectionItem> items, OnActionListener listener) {
        super(context, title);
        this.items = items;
        this.listener = listener;
        this.layout = R.layout.selection_dialog_layout;
        this.cancelable = true;
        this.dimBehind = false;
        this.swidth = "80%";
        this.sheight = "90%";
    }


    @Override
    protected void initializeUi() {
        super.initializeUi();
        rv_list = dialog.findViewById(R.id.rv_list);
        bt_cancel = dialog.findViewById(R.id.bt_cancel);
        tv_text = dialog.findViewById(R.id.tv_text);
        rg_dir = dialog.findViewById(R.id.rg_dir);
        ///////////////////////////////////////////////
        setupList();
        /////////////////////////////////////////////////
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel();
            }
        });

    }

    private void setupList() {
        rv_list.setLayoutManager(new LinearLayoutManager(context));
        /*DividerItemDecoration ver = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        rv_list.addItemDecoration(ver);*/
        SortSelectionItemAdapter adapter = new SortSelectionItemAdapter(context, items, new SortSelectionItemAdapter.OnSelectListener() {
            @Override
            public void onSelect(SelectionItem item) {
                dialog.dismiss();
                if(listener!=null)
                {
                    listener.onItemSelect(item,rg_dir.getCheckedRadioButtonId()==R.id.rb_dsc?1:-1);
                }
            }
        });
        rv_list.setAdapter(adapter);
    }

    private void onCancel() {
        if(listener!=null)
        {
            listener.onCancel();
        }
        dialog.dismiss();
    }
    ////////////////////////////////////////////

}
