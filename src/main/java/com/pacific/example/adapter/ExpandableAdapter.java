package com.pacific.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.pacific.example.adapter.ExpandableAdapterHelper;

import java.util.List;

public abstract class ExpandableAdapter<T, V> extends BaseExpandableAdapter<T, V, ExpandableAdapterHelper> {

    /**
     * Create a ExpandableAdapter.
     *
     * @param context          The context.
     * @param groupLayoutResId The layout resource id of each group item.
     * @param childLayoutResId The layout resource id of each child item.
     */
    public ExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId) {
        super(context, groupLayoutResId, childLayoutResId);
    }

    /**
     * Create a BaseExpandableAdapter.
     *
     * @param context          The context.
     * @param groupLayoutResId The layout resource id of each group item.
     * @param childLayoutResId The layout resource id of each child item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId, List<T> data) {
        super(context, groupLayoutResId, childLayoutResId, data);
    }

    @Override
    protected ExpandableAdapterHelper getAdapterHelper(int groupPosition, int childPosition, View convertView, ViewGroup parent) {
        if (childPosition == -1) {
            return ExpandableAdapterHelper.get(context, convertView, parent, groupLayoutResId, groupPosition, childPosition);
        }
        return ExpandableAdapterHelper.get(context, convertView, parent, childLayoutResId, groupPosition, childPosition);
    }
}
