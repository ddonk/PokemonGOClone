package com.example.tdmd.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.Fragments.FragmentCollection;
import com.example.tdmd.Fragments.FragmentMap;
import com.example.tdmd.Fragments.FragmentSettings;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private FragmentManager fragmentManager;
    private Pokemon pokemon;

    public MainPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;

//        ArrayList<Type> list = new ArrayList<Type>();
//        list.add(Type.Grass);
//        ArrayList<String> list1 = new ArrayList<String>();
//        list1.add("Overgrow");
//        list1.add("Unburden");
//        ArrayList<String> list2 = new ArrayList<String>();
//        list2.add("Mega Drain");
//        list2.add("Pound");

        //pokemon = new Pokemon("Treecko", list, 277, 0.5, 5.0, list1, new PokemonStats(40,45,35,65,55,70), list2, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png");

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
