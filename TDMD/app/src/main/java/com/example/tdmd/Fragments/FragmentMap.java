package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tdmd.Map.MapAdapter;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.databinding.FragmentMapBinding;

import org.osmdroid.util.GeoPoint;

import java.util.Collections;

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
        MapAdapter mapAdapter = new MapAdapter(binding.mapview, getActivity());
//        mapAdapter.InitMap();
//
//        Pokemon pokemon = new Pokemon("Treecko", Collections.singletonList(Type.Grass), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png");
//        GeoPoint geoPoint = new GeoPoint(51.5924, 4.7813);
//        mapAdapter.AddGeofence(geoPoint, pokemon, 200);
        return binding.getRoot();
    }
}