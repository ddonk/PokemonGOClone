package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.UIHandlers.ProgressBarHandler;
import com.example.tdmd.databinding.FragmentPokemonInfoStatsBinding;

public class FragmentInfoStats extends Fragment {
    private FragmentPokemonInfoStatsBinding binding;

    private static final String ARG_Pokemon = "pokemon";

    private Pokemon mPokemon;

    public FragmentInfoStats() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentInfoStats newInstance(Pokemon pokemon) {
        FragmentInfoStats fragment = new FragmentInfoStats();
        Bundle args = new Bundle();
        args.putSerializable(ARG_Pokemon, pokemon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPokemon = (Pokemon) getArguments().getSerializable(ARG_Pokemon);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokemonInfoStatsBinding.inflate(inflater, container, false);

        if(mPokemon != null) {
            LoadStats();

        }

        return binding.getRoot();
    }

    public void LoadStats() {
        ProgressBarHandler progressBarHandler = new ProgressBarHandler();

        TextView tvTextHP = binding.tvTextHP;
        ProgressBar pbHP = binding.pbHP;
        int hp = mPokemon.getPokemonStats().getHp();
        tvTextHP.setText("" + hp);
        progressBarHandler.SetProgressbarValue(hp, pbHP);

        TextView tvTextAttack = binding.tvTextAttack;
        ProgressBar pbAttack = binding.pbAttack;
        int attack = mPokemon.getPokemonStats().getAttack();
        tvTextAttack.setText("" + attack);
        progressBarHandler.SetProgressbarValue(attack, pbAttack);

        TextView tvTextDefense = binding.tvTextDefense;
        ProgressBar pbDefense = binding.pbDefense;
        int defense = mPokemon.getPokemonStats().getDefense();
        tvTextDefense.setText("" + defense);
        progressBarHandler.SetProgressbarValue(defense, pbDefense);

        TextView tvTextSpAttack = binding.tvTextSpAttack;
        ProgressBar pbSpAttack = binding.pbSpAttack;
        int spAttack = mPokemon.getPokemonStats().getSpAttack();
        tvTextSpAttack.setText("" + spAttack);
        progressBarHandler.SetProgressbarValue(spAttack, pbSpAttack);

        TextView tvTextSpDef = binding.tvTextSpDef;
        ProgressBar pbSpDef = binding.pbSpDefense;
        int spDef = mPokemon.getPokemonStats().getSpDefense();
        tvTextSpDef.setText("" + spDef);
        progressBarHandler.SetProgressbarValue(spDef, pbSpDef);

        TextView tvTextSpeed = binding.tvTextSpeed;
        ProgressBar pbSpeed = binding.pbSpeed;
        int speed = mPokemon.getPokemonStats().getSpeed();
        tvTextSpeed.setText("" + speed);
        progressBarHandler.SetProgressbarValue(speed, pbSpeed);
    }
}