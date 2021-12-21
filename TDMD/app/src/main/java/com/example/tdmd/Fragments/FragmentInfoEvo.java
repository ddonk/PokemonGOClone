package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentMapBinding;
import com.example.tdmd.databinding.FragmentPokemonInfoEvoBinding;

public class FragmentInfoEvo extends Fragment {
    private FragmentPokemonInfoEvoBinding binding;

    private static final String ARG_Pokemon = "pokemon";

    private Pokemon pokemon;

    public FragmentInfoEvo() {
        // Required empty public constructor
    }
    public static FragmentInfoEvo newInstance(Pokemon pokemon) {
        FragmentInfoEvo fragment = new FragmentInfoEvo();
        Bundle args = new Bundle();
        args.putSerializable(ARG_Pokemon, pokemon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pokemon = (Pokemon) getArguments().getSerializable(ARG_Pokemon);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokemonInfoEvoBinding.inflate(inflater, container, false);

        if(pokemon != null) {
            //initUI();
        }
        return binding.getRoot();
    }


}