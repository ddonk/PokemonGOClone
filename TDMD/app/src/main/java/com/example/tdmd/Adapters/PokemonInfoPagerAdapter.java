package com.example.tdmd.Adapters;


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

    public PokemonInfoPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);

        ArrayList<Type> list = new ArrayList<Type>();
        list.add(Type.grass);
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("Overgrow");
        list1.add("Unburden");
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("Mega Drain");
        list2.add("Pound");

        pokemon = new Pokemon("Treecko", list, 277, 0.5, 5.0, list1, new PokemonStats(40,45,35,65,55,70), new ArrayList<>(), list2, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png");

        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                FragmentInfoAbout fragmentInfoAbout = FragmentInfoAbout.newInstance(pokemon);
                return fragmentInfoAbout;
            case 1:

                FragmentInfoStats fragmentInfoStats = FragmentInfoStats.newInstance(pokemon);
                return fragmentInfoStats;
            case 2:
                FragmentInfoEvo fragmentInfoEvo = FragmentInfoEvo.newInstance();
                return fragmentInfoEvo;
            case 3:
                FragmentInfoMoves fragmentInfoMoves = FragmentInfoMoves.newInstance();
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
