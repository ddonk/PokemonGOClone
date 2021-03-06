package com.example.tdmd.Fragments;


import static com.example.tdmd.UIHandlers.TypeColorHandler.typeColor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tdmd.Adapters.PokemonInfoPagerAdapter;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.FragmentHandler;
import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentPokemonInfoBinding;
import com.squareup.picasso.Picasso;

public class FragmentInfo extends Fragment {
    private FragmentPokemonInfoBinding binding;

    private static final String ARG_Pokemon = "pokemon";

    private Pokemon pokemon;

    public FragmentInfo() {
        // Required empty public constructor
    }

    public static FragmentInfo newInstance(Pokemon pokemon) {
        FragmentInfo fragment = new FragmentInfo();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false);
        FragmentHandler.TabsFragmentsInit(new PokemonInfoPagerAdapter(getChildFragmentManager(), 3, pokemon),binding.tlInfo, binding.vpInfo, getChildFragmentManager());
        FragmentHandler.ReplaceFragment(getChildFragmentManager(), new FragmentInfoAbout(), R.id.vpInfo);
        //TODO load pokemon info correctly

        if(pokemon != null) {
            LoadPokemoninUI();
        }

        //TODO check why this button needs to be pressed twice
        ImageView backButton = binding.ivBackArrow;
        backButton.setOnClickListener(v -> {
            if(getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStackImmediate();
            }
            else {
                Toast.makeText(getContext(),"There is no previous fragment",Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    public void LoadPokemoninUI() {
        binding.tvInfoNamePokemon.setText(pokemon.getName());

        binding.tvInfoTypePokemon.setText("" + pokemon.getTypes().get(0));

        if(pokemon.getTypes().size() > 1) {
            binding.tvInfoTypePokemon1.setText("" + pokemon.getTypes().get(1));
        } else {
            binding.tvInfoTypePokemon1.setVisibility(View.GONE);
        }

        binding.ivBackground.setColorFilter(typeColor(pokemon.getTypes().get(0)));
        Picasso.get().load(pokemon.getImageurl()).into(binding.ivPokemon);
    }
}