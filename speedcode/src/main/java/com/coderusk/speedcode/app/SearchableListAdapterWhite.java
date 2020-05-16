package com.coderusk.speedcode.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.coderusk.speedcode.R;

import java.util.ArrayList;

public class SearchableListAdapterWhite extends RecyclerView.Adapter<SearchableListAdapterWhite.MyViewHolder> {
    private int selectedIndex = -1;
    private Context context;
    private ArrayList<String> values;

    public interface OnSelectListener
    {
        void onSelect(int index, String state);
    }

    private OnSelectListener listener = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_value;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_value = itemView.findViewById(R.id.tv_value);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchableListAdapterWhite(Context context, ArrayList<String> values, OnSelectListener listener) {
        this.values = values;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SearchableListAdapterWhite.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout_white, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final String value = values.get(position);
        holder.tv_value.setText(value);


        holder.tv_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(position,value);
            }
        });
    }

    private void setState(int index, String state) {
        if(listener!=null)
        {
            listener.onSelect(index, state);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }
}
