package com.example.tdmd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tdmd.Fragments.FragmentInfo;
import com.example.tdmd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentHandler.TabsFragmentsInit(findViewById(R.id.MainTabLayout), findViewById(R.id.MainViewPager) ,getSupportFragmentManager());

        FragmentHandler.ReplaceFragment(getSupportFragmentManager(), new FragmentInfo(), R.id.MainViewPager);
    }
}
