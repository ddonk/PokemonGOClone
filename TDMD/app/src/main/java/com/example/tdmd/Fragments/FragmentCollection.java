package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.Adapters.RecyclerAdapter;
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

    PokemonStats pokemonStats = new PokemonStats(10,20,30,40,50,60);
    String[] strings = new String[]{"Test1", "Test2", "Test3", "Test4"};
    Pokemon[] pokemons = {new Pokemon("Treecko", Collections.singletonList(Type.Grass),10,20.0,30.0, Collections.singletonList("Ember"), pokemonStats, Arrays.asList(strings),"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png"),
            new Pokemon("Pikachu", Collections.singletonList(Type.Electric), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
            new Pokemon("Squirtle", Collections.singletonList(Type.Water), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png")};
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

        recyclerView.setAdapter(new RecyclerAdapter(pokemons));

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
}