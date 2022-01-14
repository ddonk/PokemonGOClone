package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.Adapters.RecyclerAdapter;
import com.example.tdmd.SharedPreferencesManager;
import com.example.tdmd.databinding.FragmentCollectionBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCollection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCollection extends Fragment {
    private FragmentCollectionBinding binding;
    public FragmentCollection() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentCollection newInstance() {
        FragmentCollection fragment = new FragmentCollection();
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
        binding = FragmentCollectionBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.rvCollection;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(binding.getRoot().getContext());
        recyclerView.setAdapter(new RecyclerAdapter(sharedPreferencesManager.GetPokemon()));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(binding.getRoot().getContext());
        binding.rvCollection.setAdapter(new RecyclerAdapter(sharedPreferencesManager.GetPokemon()));
    }
}