package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Adapters.RecyclerAdapter;
import com.example.tdmd.SharedPreferencesManager;
import com.example.tdmd.databinding.FragmentCollectionBinding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCollectionBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.rvCollection;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SwipeRefreshLayout swipeRefreshLayout = binding.swiperefresh;

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

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            RefreshPokemon();
        });

        return binding.getRoot();
    }

    private void RefreshPokemon() {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(binding.getRoot().getContext());
        binding.rvCollection.setAdapter(new RecyclerAdapter(sharedPreferencesManager.GetPokemon()));
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(binding.getRoot().getContext());
        binding.rvCollection.setAdapter(new RecyclerAdapter(sharedPreferencesManager.GetPokemon()));
    }
}