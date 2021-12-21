package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentMapBinding;
import com.example.tdmd.databinding.FragmentPokemonInfoEvoBinding;

public class FragmentInfoEvo extends Fragment {
    private FragmentPokemonInfoEvoBinding binding;

    public FragmentInfoEvo() {
        // Required empty public constructor
    }
    public static FragmentInfoEvo newInstance() {
        FragmentInfoEvo fragment = new FragmentInfoEvo();
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
        binding = FragmentPokemonInfoEvoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}