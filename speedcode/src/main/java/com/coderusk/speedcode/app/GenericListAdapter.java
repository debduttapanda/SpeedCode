package com.coderusk.speedcode.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GenericListAdapter extends RecyclerView.Adapter<GenericListAdapter.MyViewHolder> {
    private int selectedIndex = -1;
    private Context context;
    private ArrayList<?> datas;
    private int viewId;
    private int layoutId;

    private OnEventListener listener = null;

    public GenericListAdapter(Context context, int viewId, int layoutId, ArrayList<?> datas, OnEventListener listener) {
        this.datas = datas;
        this.context = context;
        this.listener = listener;
        this.viewId = viewId;
        this.layoutId = layoutId;
    }

    protected int getViewId()
    {
        return viewId;
    }
    @Override
    public GenericListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(getItemLayout()==0)
        {
            return null;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    protected int getItemLayout()
    {
        return layoutId;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Object itemData = datas.get(position);
        holder.view.setData(itemData);
        holder.view.setListener(args -> {
            if(listener!=null)
            {
                listener.onEvent(args);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (datas == null) {
            return 0;
        }
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public GenericCustomLayoutView view;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(getViewId()==0)
            {
                return;
            }
            view = itemView.findViewById(getViewId());
        }
    }
}
