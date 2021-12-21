package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.databinding.FragmentPokemonInfoMovesBinding;

public class FragmentInfoMoves extends Fragment {
    private FragmentPokemonInfoMovesBinding binding;

    private static final String ARG_Pokemon = "pokemon";

    private Pokemon mPokemon;

    public FragmentInfoMoves() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentInfoMoves newInstance(Pokemon pokemon) {
        FragmentInfoMoves fragment = new FragmentInfoMoves();
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
        binding = FragmentPokemonInfoMovesBinding.inflate(inflater, container, false);

        if(mPokemon != null) {
            //TODO Load Moves
            //initUI();
        }

        return binding.getRoot();
    }
}