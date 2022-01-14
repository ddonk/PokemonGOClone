package com.example.tdmd;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FragmentHandler extends androidx.fragment.app.FragmentManager {

    public static void TabsFragmentsInit(PagerAdapter pagerAdapter, TabLayout tabLayout, ViewPager viewPager, FragmentManager fragmentManager) {
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
        fragmentManager.beginTransaction().replace(fragmentContainer, fragment).addToBackStack(null).commit();
    }
}
