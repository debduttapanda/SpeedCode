package com.coderusk.speedcode.app;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class GenericCustomRecyclerView extends RecyclerView {

    private Context context;
    ArrayList<?> datas = null;
    OnEventListener listener = null;

    public OnEventListener getListener() {
        return listener;
    }

    public void setListener(OnEventListener listener) {
        this.listener = listener;
    }

    public ArrayList<?> getDatas() {
        return datas;
    }

    private void setup()
    {
        GenericListAdapter adapter = new GenericListAdapter(context, getViewId(),getLayoutId(),datas, args -> {
            if(listener!=null)
            {
                listener.onEvent(args);
            }
        });

        setLayoutManager(new LinearLayoutManager(context));
        addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        setAdapter(adapter);
    }

    protected abstract int getLayoutId();

    protected abstract int getViewId();

    public void setDatas(ArrayList<?> datas)
    {
        this.datas = datas;
        setup();
    }
    private void commonConstructor(Context context)
    {
        this.context = context;
    }

    public GenericCustomRecyclerView(@NonNull Context context) {
        super(context);
        commonConstructor(context);
    }

    public GenericCustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        commonConstructor(context);
    }

    public GenericCustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonConstructor(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(getFullExpandable())
        {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }

    protected boolean getFullExpandable()
    {
        return false;
    }
}
