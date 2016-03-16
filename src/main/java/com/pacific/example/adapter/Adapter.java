package com.pacific.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Using the provided RecycleAdapterHelper, your code is minimalist.
 *
 * @param <T> The type of the items in the list.
 */

public abstract class Adapter<T> extends BaseAdapter<T, com.pacific.example.adapter.AdapterHelper> {

    /**
     * Create a Adapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public Adapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    /**
     * Same as Adapter#Adapter(Context,int) but with
     * some initialization data.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public Adapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    protected AdapterHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return AdapterHelper.get(context, convertView, parent, layoutResId, position);
    }

}
