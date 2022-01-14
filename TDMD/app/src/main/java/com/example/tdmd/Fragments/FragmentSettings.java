package com.example.tdmd.Fragments;

import static com.example.tdmd.UIHandlers.TypeColorHandler.typeColor;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tdmd.Adapters.PokemonInfoPagerAdapter;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.databinding.FragmentSettingsBinding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSettings extends Fragment {
    private FragmentSettingsBinding binding;

    public FragmentSettings() {
        // Required empty public constructor
    }

    public static FragmentSettings newInstance() {
        FragmentSettings fragment = new FragmentSettings();
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
        // Inflate the layout for this fragment'

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}