package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentMapBinding;
import com.example.tdmd.databinding.FragmentPokemonInfoMovesBinding;

public class FragmentInfoMoves extends Fragment {
    private FragmentPokemonInfoMovesBinding binding;

    public FragmentInfoMoves() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentInfoMoves newInstance() {
        FragmentInfoMoves fragment = new FragmentInfoMoves();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        binding = FragmentPokemonInfoMovesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}