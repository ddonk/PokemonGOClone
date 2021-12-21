package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentMapBinding;
import com.example.tdmd.databinding.FragmentPokemonInfoAboutBinding;

public class FragmentInfoAbout extends Fragment {
    private FragmentPokemonInfoAboutBinding binding;
    private static Pokemon POKEMON;

    public FragmentInfoAbout() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentInfoAbout newInstance(Pokemon pokemon) {
        FragmentInfoAbout fragment = new FragmentInfoAbout();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putSerializable(String.valueOf(POKEMON), pokemon);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokemonInfoAboutBinding.inflate(inflater, container, false);
        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        TextView tvHeight = binding.tvTextHeight;
        tvHeight.setText(POKEMON.getHeight() + " m");

        TextView tvWeight = binding.tvTextWeight;
        tvWeight.setText(POKEMON.getWeight() + " kg");

        TextView tvAbilities = binding.tvTextAbilities;
        String result = POKEMON.getAbilities().get(0);

        for (int i = 1; i < POKEMON.getAbilities().size(); i++) {
            result += ", " + POKEMON.getAbilities().get(i);
        }

        tvAbilities.setText(result);
    }
}