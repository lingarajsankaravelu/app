package com.pacific.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.List;

public abstract class RecyclerAdapter<T> extends BaseRecyclerAdapter<T, com.pacific.example.adapter.RecyclerAdapterHelper> {

    public RecyclerAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public RecyclerAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected com.pacific.example.adapter.RecyclerAdapterHelper getAdapterHelper(ViewHolder viewHolder) {
        return com.pacific.example.adapter.RecyclerAdapterHelper.get(viewHolder);
    }
}
