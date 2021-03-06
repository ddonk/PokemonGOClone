package com.example.tdmd.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Fragments.FragmentCollection;
import com.example.tdmd.Fragments.FragmentMap;
import com.example.tdmd.Fragments.FragmentSettings;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MainPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentMap.newInstance();
            case 1:
                return FragmentCollection.newInstance();
            case 2:
                return FragmentSettings.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
