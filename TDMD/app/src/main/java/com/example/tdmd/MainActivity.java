package com.example.tdmd;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.tdmd.Adapters.MainPagerAdapter;
import com.example.tdmd.Adapters.RESTAPIAdapter;
import com.example.tdmd.Fragments.FragmentInfo;
import com.example.tdmd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentHandler.TabsFragmentsInit(new MainPagerAdapter(getSupportFragmentManager(), 3),findViewById(R.id.MainTabLayout), findViewById(R.id.MainViewPager) ,getSupportFragmentManager());

        FragmentHandler.ReplaceFragment(getSupportFragmentManager(), new FragmentInfo(), R.id.MainViewPager);

        RESTAPIAdapter restapiAdapter = new RESTAPIAdapter(this);
        restapiAdapter.GetPokemon();
    }
}
