package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            TextView tvMoves = binding.tvMove;
            TextView tvMoves1 = binding.tvMove1;
            TextView tvMoves2 = binding.tvMove2;
            TextView tvMoves3 = binding.tvMove3;

            tvMoves.setText("Move #1: " + mPokemon.getMoves().get(0));
            tvMoves1.setText("Move #2: " + mPokemon.getMoves().get(1));
            tvMoves2.setText("Move #3: " + mPokemon.getMoves().get(2));
            tvMoves3.setText("Move #4: " + mPokemon.getMoves().get(3));

        }

        return binding.getRoot();
    }
}