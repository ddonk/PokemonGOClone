package com.example.tdmd.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.R;
import com.example.tdmd.databinding.FragmentMapBinding;

public class GeofencingBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Geofencing", "Triggering");
        Toast.makeText(context, "Geofencing triggered...", Toast.LENGTH_LONG).show();
        //Button button = rootView.findViewById();
        //binding = FragmentMapBinding.inflate()
        Bundle bundle = intent.getBundleExtra("Bundle");
        Pokemon pokemon = (Pokemon) bundle.getSerializable("Pokemon");
        Log.d("Geofencing", pokemon.toString());
    }
}