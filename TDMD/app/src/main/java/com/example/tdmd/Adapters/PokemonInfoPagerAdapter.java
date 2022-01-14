package com.example.tdmd.Adapters;


import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.FragmentHandler;
import com.example.tdmd.Fragments.FragmentInfo;
import com.example.tdmd.Fragments.FragmentInfoAbout;
import com.example.tdmd.Fragments.FragmentInfoEvo;
import com.example.tdmd.Fragments.FragmentInfoMoves;
import com.example.tdmd.Fragments.FragmentInfoStats;
import com.example.tdmd.R;

import java.util.ArrayList;

public class PokemonInfoPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private Pokemon pokemon;
    private FragmentManager fragmentManager;

    public PokemonInfoPagerAdapter(FragmentManager fragmentManager, int tabCount, Pokemon pokemon) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        Log.d("TESTING", "Getting items");
        this.numOfTabs = tabCount;
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
                FragmentHandler.ReplaceFragment(fragmentManager, fragmentInfoAbout , R.id.vpInfo);

                return fragmentInfoAbout;
            case 1:
                Log.d("TESTING", "FRAGMENT INFO STATS CREATE");
                FragmentInfoStats fragmentInfoStats = FragmentInfoStats.newInstance(pokemon);
                //FragmentHandler.ReplaceFragment(fragmentManager, fragmentInfoStats , R.id.vpInfo);

                return fragmentInfoStats;
            case 2:
                Log.d("TESTING", "FRAGMENT INFO MOVES CREATE");
                FragmentInfoMoves fragmentInfoMoves = FragmentInfoMoves.newInstance(pokemon);
                FragmentHandler.ReplaceFragment(fragmentManager, fragmentInfoMoves , R.id.vpInfo);

                return fragmentInfoMoves;
//            case 3:
//                Log.d("TESTING", "FRAGMENT INFO MOVES CREATE");
//                FragmentInfoEvo fragmentInfoEvo = FragmentInfoEvo.newInstance(pokemon);
//                FragmentHandler.ReplaceFragment(fragmentManager, fragmentInfoEvo , R.id.vpInfo);
//
//                return fragmentInfoEvo;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
