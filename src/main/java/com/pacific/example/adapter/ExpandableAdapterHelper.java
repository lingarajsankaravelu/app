package com.pacific.example.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <b>Usage</b>
 * <p/>
 * return AdapterHelper.get(context, convertView, parent, R.layout.item)
 * .setText(R.id.tvName, contact.getName())
 * .setText(R.id.tvEmails, contact.getEmails().toString())
 * .setText(R.id.tvNumbers, contact.getNumbers().toString())
 * .getView();
 * <p/>
 */
final public class ExpandableAdapterHelper extends BaseAdapterHelper<ExpandableAdapterHelper> {

    protected View convertView;
    protected int groupPosition = -1;
    protected int childPosition = -1;

    private ExpandableAdapterHelper(Context context, ViewGroup parent, int layoutId, int groupPosition, int childPosition) {
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.views = new SparseArray();
        this.convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.convertView.setTag(this);
    }

    /**
     * This method is the only entry point to get a AdapterHelper.
     *
     * @param context     The current context.
     * @param convertView The convertView arg passed to the getView() method.
     * @param parent      The parent arg passed to the getView() method.
     * @param layoutId    The item view layout resource.
     * @return A AdapterHelper instance.
     */
    static ExpandableAdapterHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int groupPosition, int childPosition) {
        if (convertView == null) {
            return new ExpandableAdapterHelper(context, parent, layoutId, groupPosition, childPosition);
        }
        ExpandableAdapterHelper helper = (ExpandableAdapterHelper) convertView.getTag();
        helper.groupPosition = groupPosition;
        helper.childPosition = childPosition;
        return helper;
    }

    @Override
    public View getItemView() {
        return convertView;
    }

    /**
     * Retrieve the overall position of the data in the list.
     *
     * @throws IllegalArgumentException If the position hasn't been set at the construction of the this helper.
     */
    public int getGroupPosition() {
        return groupPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }
}
