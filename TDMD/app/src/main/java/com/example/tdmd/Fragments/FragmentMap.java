package com.example.tdmd.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.tdmd.Map.MapAdapter;
import com.example.tdmd.databinding.FragmentMapBinding;

public class FragmentMap extends Fragment {
    private FragmentMapBinding binding;
    private Button button;

    public FragmentMap() {
        // Required empty public constructor
    }

    public static FragmentMap newInstance() {
        FragmentMap fragment = new FragmentMap();
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
        binding = FragmentMapBinding.inflate(inflater, container, false);
        this.button = binding.mvButton;
        button.setVisibility(View.VISIBLE);
        MapAdapter mapAdapter = new MapAdapter(getActivity(), binding);
        return binding.getRoot();
    }
}