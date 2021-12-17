package com.example.tdmd.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.R;
import com.example.tdmd.Adapters.RecyclerAdapter;

import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Pokemon[] pokemons = {new Pokemon("Treecko", Collections.singletonList(Type.Grass), "placeholder"), new Pokemon("Pikachu", Collections.singletonList(Type.Electric), "placeholder"), new Pokemon("Squirtle", Collections.singletonList(Type.Water), "placeholder")};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvCollection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        String[] names = new String[pokemons.length];
        Type[] types = new Type[pokemons.length];

        for (int i = 0; i < pokemons.length; i++) {
            names[i] = pokemons[i].getName();
            types[i] = pokemons[i].getTypes().get(0);
        }

        recyclerView.setAdapter(new RecyclerAdapter(names, types));

        return view;
    }
}