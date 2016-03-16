package com.pacific.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.pacific.example.adapter.ExpandableAdapterHelper;

import java.util.ArrayList;
import java.util.List;

abstract class BaseExpandableAdapter<T, V, H extends ExpandableAdapterHelper> extends BaseExpandableListAdapter implements DataIO<T> {

    protected ArrayList<T> data;
    protected Context context;
    protected final int groupLayoutResId;
    protected final int childLayoutResId;

    /**
     * Create a ExpandableAdapter
     *
     * @param context          The context.
     * @param groupLayoutResId The layout resource id of each group item.
     * @param childLayoutResId The layout resource id of each child item.
     */
    public BaseExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId) {
        this(context, groupLayoutResId, childLayoutResId, null);
    }

    /**
     * Create a ExpandableAdapter
     *
     * @param context          The context.
     * @param groupLayoutResId The layout resource id of each group item.
     * @param childLayoutResId The layout resource id of each child item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public BaseExpandableAdapter(Context context, int groupLayoutResId, int childLayoutResId, List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.context = context;
        this.groupLayoutResId = groupLayoutResId;
        this.childLayoutResId = childLayoutResId;
    }

    @Override
    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        data.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        data.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void remove(List<T> list) {
        data.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (data != null && data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
        replaceAt(data.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elem) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public T get(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public ArrayList<T> getAll() {
        return data;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean contains(T elem) {
        return data.contains(elem);
    }

    @Override
    public int getGroupCount() {
        return getSize();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition < getGroupCount()) {
            List<V> children = getChildren(groupPosition);
            if (children != null) {
                return children.size();
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public T getGroup(int groupPosition) {
        return get(groupPosition);
    }

    @Override
    public V getChild(int groupPosition, int childPosition) {
        if (groupPosition < getGroupCount() && childPosition < getChildrenCount(groupPosition)) {
            return getChildren(groupPosition).get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if (groupPosition < getGroupCount()) {
            return childPosition;
        }
        return -1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        H help = getAdapterHelper(groupPosition, -1, convertView, parent);
        T groupItem = getGroup(groupPosition);
        convertGroupView(isExpanded, help, groupItem);
        return help.getItemView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        H help = getAdapterHelper(groupPosition, childPosition, convertView, parent);
        V childItem = getChild(groupPosition, childPosition);
        convertChildView(isLastChild, help, childItem);
        return help.getItemView();
    }


    protected abstract List<V> getChildren(int groupPosition);

    /**
     * Implement this method and use the helper to adapt the view to the given
     * item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convertGroupView(boolean isExpanded, H helper, T item);

    protected abstract void convertChildView(boolean isLastChild, H helper, V item);

    /**
     * You can override this method to use a custom RecycleAdapterHelper in order
     * to fit your needs
     *
     * @param groupPosition the position of the group for which the View is
     *                      item whose view we want.
     * @param childPosition the position of the child (for which the View is
     *                      returned) within the group
     * @param convertView   the old view to reuse, if possible.You should check
     *                      that this view is non-null and of an appropriate type before
     *                      using. If it is not possible to convert this view to display
     *                      the correct data, this method can create a new view.
     * @param parent        The parent that this view will eventually be attached to
     * @return An instance of RecycleAdapterHelper
     */
    protected abstract H getAdapterHelper(int groupPosition, int childPosition, View convertView, ViewGroup parent);
}
