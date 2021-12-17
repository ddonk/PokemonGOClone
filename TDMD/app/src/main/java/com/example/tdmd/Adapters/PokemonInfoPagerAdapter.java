package com.example.tdmd.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Fragments.PokemonInfoAbout;
import com.example.tdmd.Fragments.PokemonInfoEvo;
import com.example.tdmd.Fragments.PokemonInfoMoves;
import com.example.tdmd.Fragments.PokemonInfoStats;

public class PokemonInfoPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private PokemonUIAdapter pokemonUIAdapter;
    private Pokemon currentPokemon;

    public PokemonInfoPagerAdapter(FragmentManager fm, int numOfTabs, PokemonUIAdapter pokemonUIAdapter) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.pokemonUIAdapter = pokemonUIAdapter;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                pokemonUIAdapter.LoadAboutPage(currentPokemon);
                return new PokemonInfoAbout();
            case 1:
                pokemonUIAdapter.LoadStats(currentPokemon);
                return new PokemonInfoStats();
            case 2:
                pokemonUIAdapter.LoadEvolutions(currentPokemon);
                return new PokemonInfoEvo();
            case 3:
                pokemonUIAdapter.LoadMoves(currentPokemon);
                return new PokemonInfoMoves();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public void setCurrentPokemon(Pokemon currentPokemon) {
        this.currentPokemon = currentPokemon;
    }
}
