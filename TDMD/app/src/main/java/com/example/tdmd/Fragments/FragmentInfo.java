package com.example.tdmd.Fragments;


import static com.example.tdmd.UIHandlers.TypeColorHandler.typeColor;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.FragmentHandler;
import com.example.tdmd.databinding.FragmentPokemonInfoBinding;

public class FragmentInfo extends Fragment {
    private FragmentPokemonInfoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Name = "param1";
    private static final String ARG_Type1 = "param2";
    private static final String ARG_Type2 = "param3";
    private static final String ARG_ImgURL = "param4";
    private static final Pokemon ARG_Pokemon = null;



    // TODO: Rename and change types of parameters
    private String name;
    private String type1;
    private String type2;
    private String imgUrl;
    private Pokemon pokemon;

    public FragmentInfo() {
        // Required empty public constructor
    }

    public static FragmentInfo newInstance(Pokemon pokemon) {
        FragmentInfo fragment = new FragmentInfo();
        Bundle args = new Bundle();
        args.putString(ARG_Name, pokemon.getName());
        args.putString(ARG_Type1, "" + pokemon.getTypes().get(0));

        if(pokemon.getTypes().size() > 1) {
            args.putString(ARG_Type2, "" + pokemon.getTypes().get(1));
        }

        args.putString(ARG_ImgURL, "" + pokemon.getImageurl());
        //args.putExtra(String.valueOf(ARG_Pokemon), pokemon);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_Name);
            type1 = getArguments().getString(ARG_Type1);
            type2 = getArguments().getString(ARG_Type2);
            imgUrl = getArguments().getString(ARG_ImgURL);
            //pokemon = (Pokemon) getArguments().getSerializable(String.valueOf(ARG_Pokemon));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Fragments", "On Create View of Fragment Info");
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false);
        //FragmentHandler.TabsFragmentsInit(binding.tlInfo, binding.vpInfo, getFragmentManager());

        LoadPokemoninUI();
        return binding.getRoot();
    }

    public void LoadPokemoninUI() {
        binding.ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Make back button
                //FragmentHandler.ReplaceFragment(getFragmentManager(), new );
            }
        });

        //Log.d("TESTING", "Received Pokemon: " + pokemon.toString());
        TextView tvInfoName = binding.tvInfoNamePokemon;

        tvInfoName.setText(name);

        TextView tvType = binding.tvInfoTypePokemon;
        tvType.setText(type1);

        TextView tvType1 = binding.tvInfoTypePokemon1;

//        if(pokemon.getTypes().size() > 1) {
//
//            tvType1.setText(pokemon.getTypes().get(1).name());
//        } else {
//            tvType1.setVisibility(View.INVISIBLE);
//        }

        ImageView ivBackground = binding.ivBackground;

        //ivBackground.setColorFilter(typeColor(Type.valueOf(type1)), PorterDuff.Mode.MULTIPLY);

        ImageView ivPokemon = binding.ivPokemon;

        //Picasso.get().load(pokemon.getImageurl()).into(ivPokemon);
//
//        if(pokemon.getAbilities() != null) {
//            TextView tvID = binding.tvIDPokemon;
//            tvID.setText("#" + pokemon.getID());
//        }
    }
}