package com.pacific.example;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        toolbar = retrieveView(R.id.toolbar);
        tabLayout = retrieveView(R.id.tab_layout);
        viewPager = retrieveView(R.id.view_pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.action_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.action_explore);
        tabLayout.getTabAt(2).setIcon(R.drawable.action_setting);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.setTitle(R.string.title_navigation_fragment);
                        break;
                    case 1:
                        toolbar.setTitle(R.string.title_setting_fragment);
                        break;
                    default:
                        toolbar.setTitle(R.string.title_explore_fragment);
                        break;
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    protected <V extends View> V retrieveView(int viewId) {
        return (V) findViewById(viewId);
    }


    public static class PagerAdapter extends FragmentPagerAdapter {
        private int count;

        public PagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.count = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ListViewFragment.newInstance();
                case 1:
                    return ExpandableListViewFragment.newInstance();
                default:
                    return RecyclerViewFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
