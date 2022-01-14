package com.example.tdmd.Adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Fragments.FragmentInfoAbout;
import com.example.tdmd.Fragments.FragmentInfoMoves;
import com.example.tdmd.Fragments.FragmentInfoStats;

public class PokemonInfoPagerAdapter extends FragmentPagerAdapter{
    private int numOfTabs;
    private Pokemon pokemon;
    public PokemonInfoPagerAdapter(FragmentManager fm, int numOfTabs, Pokemon pokemon) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentInfoAbout.newInstance(pokemon);
            case 1:
                return FragmentInfoStats.newInstance(pokemon);
            case 2:
                return FragmentInfoMoves.newInstance(pokemon);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
