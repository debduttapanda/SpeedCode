package com.coderusk.speedcode.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.coderusk.speedcode.R;

import java.util.ArrayList;

public class SortSelectionItemAdapter extends RecyclerView.Adapter<SortSelectionItemAdapter.MyViewHolder> {
    private int selectedIndex = -1;
    private Context context;
    private ArrayList<SelectionItem> items;
    private OnSelectListener listener = null;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SortSelectionItemAdapter(Context context, ArrayList<SelectionItem> items, OnSelectListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SortSelectionItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SelectionItem item = items.get(position);
        holder.tv_text.setText(item.getText());
        if(item.getDrawable()!=0)
        {
            holder.iv_icon.setImageDrawable(context.getResources().getDrawable(item.getDrawable()));
        }
        else
        {
            holder.iv_icon.setVisibility(View.GONE);
        }
        holder.root.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSelect(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnSelectListener {
        void onSelect(SelectionItem item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_icon;
        public TextView tv_text;
        public View root;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_text = itemView.findViewById(R.id.tv_text);
            root = itemView.findViewById(R.id.root);
        }
    }
}
