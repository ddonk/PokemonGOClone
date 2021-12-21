package com.example.tdmd.Adapters;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.Fragments.FragmentInfoAbout;
import com.example.tdmd.Fragments.FragmentInfoEvo;
import com.example.tdmd.Fragments.FragmentInfoMoves;
import com.example.tdmd.Fragments.FragmentInfoStats;

import java.util.ArrayList;

public class PokemonInfoPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private Pokemon pokemon;
    public PokemonInfoPagerAdapter(FragmentManager fragmentManager, int tabCount, Pokemon pokemon) {
        super(fragmentManager);
        Log.d("TESTING", "Getting items");
        this.numOfTabs = numOfTabs;
        this.pokemon = pokemon;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("TESTING", "Getting items");
        switch (position) {
            case 0:
                Log.d("TESTING", "FRAGMENT INFO ABOUT CREATE");
                FragmentInfoAbout fragmentInfoAbout = FragmentInfoAbout.newInstance(pokemon);
                return fragmentInfoAbout;
            case 1:
                Log.d("TESTING", "FRAGMENT INFO STATS CREATE");
                FragmentInfoStats fragmentInfoStats = FragmentInfoStats.newInstance(pokemon);
                return fragmentInfoStats;
            case 2:
                Log.d("TESTING", "FRAGMENT INFO EVO CREATE");
                FragmentInfoEvo fragmentInfoEvo = FragmentInfoEvo.newInstance(pokemon);
                return fragmentInfoEvo;
            case 3:
                Log.d("TESTING", "FRAGMENT INFO MOVES CREATE");
                FragmentInfoMoves fragmentInfoMoves = FragmentInfoMoves.newInstance(pokemon);
                return fragmentInfoMoves;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
