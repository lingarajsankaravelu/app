package com.pacific.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pacific.example.adapter.Adapter;
import com.pacific.example.adapter.AdapterHelper;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    private ListView listView;
    private Adapter<ExploreBean> adapter;

    public ListViewFragment() {
    }

    public static ListViewFragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new Adapter<ExploreBean>(getContext(), R.layout.item) {
            @Override
            protected void convert(final AdapterHelper helper, ExploreBean exploreBean) {
                helper.setImageResource(R.id.img_explore_icon, exploreBean.getIconResId());
                final int position = helper.getPosition();
                helper.setText(R.id.tv_explore_name, "____Index: " + String.valueOf(position));
                helper.setText(R.id.tv_explore_desc, exploreBean.getDescription());
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickSnack(position);
                    }
                });
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
        load();
    }

    public void load() {
        List<ExploreBean> list = new ArrayList<>();
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        list.add(new ExploreBean(R.drawable.web, "web work", "start：2016.01.01，end: 2016.02.01"));
        adapter.addAll(list);
    }

    public void clickSnack(int position) {
        Snackbar.make(listView, "click item " + String.valueOf(position), Snackbar.LENGTH_SHORT)
                .setAction(R.string.close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }
}
