package com.example.tdmd.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Fragments.CollectionFragment;
import com.example.tdmd.Fragments.MapFragment;
import com.example.tdmd.Fragments.PokemonInfoAbout;
import com.example.tdmd.Fragments.PokemonInfoEvo;
import com.example.tdmd.Fragments.PokemonInfoMoves;
import com.example.tdmd.Fragments.PokemonInfoStats;
import com.example.tdmd.Fragments.SettingsFragment;

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
                return new MapFragment();
            case 1:
                return new CollectionFragment();
            case 2:
                return new SettingsFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
