package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.databinding.FragmentPokemonInfoAboutBinding;

public class FragmentInfoAbout extends Fragment {
    private FragmentPokemonInfoAboutBinding binding;
    private String weight;
    private String height;

    private static final String ARG_Pokemon = "pokemon";

    private Pokemon mPokemon;

    public FragmentInfoAbout() {
        // Required empty public constructor
    }

    public static FragmentInfoAbout newInstance(Pokemon pokemon) {
        FragmentInfoAbout fragment = new FragmentInfoAbout();
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
        binding = FragmentPokemonInfoAboutBinding.inflate(inflater, container, false);

        if(mPokemon != null) {
            initUI();
        }

        return binding.getRoot();
    }

    private void initUI() {
        TextView tvHeight = binding.tvTextHeight;
        height = mPokemon.getHeight() + " m";
        tvHeight.setText(height);

//        TextView tvWeight = binding.tvTextWeight;
//        weight = mPokemon.getWeight() + " kg";
//        tvWeight.setText(weight);
//
//        TextView tvAbilities = binding.tvTextAbilities;
//        String result = mPokemon.getAbilities().get(0);
//
//        for (int i = 1; i < mPokemon.getAbilities().size(); i++) {
//            result += ", " + mPokemon.getAbilities().get(i);
//        }
//
//        tvAbilities.setText(result);
    }
}