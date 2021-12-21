package com.example.tdmd;

import android.app.Activity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tdmd.Adapters.MainPagerAdapter;
import com.example.tdmd.Adapters.PokemonInfoPagerAdapter;
import com.example.tdmd.Fragments.FragmentInfo;
import com.google.android.material.tabs.TabLayout;

public class FragmentHandler extends androidx.fragment.app.FragmentManager {

    public static void TabsFragmentsInit(TabLayout tabLayout, ViewPager viewPager, FragmentManager fragmentManager) {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(fragmentManager, tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener)(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(@androidx.annotation.Nullable TabLayout.Tab tab) {
                if (tab != null) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            public void onTabUnselected(@androidx.annotation.Nullable TabLayout.Tab tab) {
            }

            public void onTabReselected(@Nullable TabLayout.Tab tab) {
                if (tab != null) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }
        }));
    }

    public static void ReplaceFragment(FragmentManager fragmentManager, Fragment fragment, int fragmentContainer) {
        fragmentManager.beginTransaction().replace(fragmentContainer, fragment).commit();
    }
}
