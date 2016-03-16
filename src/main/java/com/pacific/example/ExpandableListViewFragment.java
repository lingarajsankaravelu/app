package com.pacific.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.pacific.example.adapter.ExpandableAdapter;
import com.pacific.example.adapter.ExpandableAdapterHelper;

import java.util.ArrayList;
import java.util.List;


public class ExpandableListViewFragment extends Fragment {

    private ExpandableListView listView;
    private ExpandableAdapter<MenuBean, ExploreBean> adapter;

    public ExpandableListViewFragment() {
    }

    public static ExpandableListViewFragment newInstance() {
        ExpandableListViewFragment fragment = new ExpandableListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ExpandableAdapter<MenuBean, ExploreBean>(getContext(), R.layout.item, R.layout.item_child) {
            @Override
            protected List<ExploreBean> getChildren(int groupPosition) {
                return get(groupPosition).getExploreBeanList();
            }

            @Override
            protected void convertGroupView(final boolean isExpanded, final ExpandableAdapterHelper helper, MenuBean item) {
                helper.setImageResource(R.id.img_explore_icon, item.getIconResId())
                        .setText(R.id.tv_explore_name, item.getDescription())
                        .getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isExpanded) {
                            listView.collapseGroup(helper.getGroupPosition());
                        } else {
                            listView.expandGroup(helper.getGroupPosition());
                        }
                    }
                });
            }

            @Override
            protected void convertChildView(boolean isLastChild, final ExpandableAdapterHelper helper, ExploreBean item) {
                helper.setText(R.id.tv_explore_name, item.getDescription())
                        .getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSnack(helper.getGroupPosition(), helper.getChildPosition());
                    }
                });
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expandable_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ExpandableListView) view.findViewById(R.id.elv_list);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        listView.setAdapter(adapter);
        load();
    }

    public void clickSnack(int g, int c) {
        String str = "click group " + String.valueOf(g) + " child " + String.valueOf(c);
        Snackbar.make(listView, str, Snackbar.LENGTH_SHORT)
                .setAction(R.string.close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    public void load() {
        List<MenuBean> list0 = new ArrayList<>();

        list0.add(new MenuBean(R.drawable.smart_ticket, getString(R.string.smart_ticket)));
        list0.add(new MenuBean(R.drawable.auto_lock, getString(R.string.auto_lock)));
        list0.add(new MenuBean(R.drawable.check_device, getString(R.string.check_device)));
        list0.add(new MenuBean(R.drawable.e_key, getString(R.string.e_key)));
        list0.add(new MenuBean(R.drawable.helper, getString(R.string.helper)));
        list0.add(new MenuBean(R.drawable.security_monitor, getString(R.string.security_monitor)));
        list0.add(new MenuBean(R.drawable.check_state, getString(R.string.check_state)));
        list0.add(new MenuBean(R.drawable.work_instruction, getString(R.string.work_instruction)));
        list0.add(new MenuBean(R.drawable.web, getString(R.string.web)));
        list0.add(new MenuBean(R.drawable.repair_manage, getString(R.string.repair_manage)));

        for (MenuBean menuBean : list0) {
            List<ExploreBean> list = new ArrayList<>();
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
            menuBean.setExploreBeanList(list);
        }

        adapter.addAll(list0);
    }
}
