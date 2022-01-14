package com.example.tdmd.Map;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.SharedPreferencesManager;
import com.example.tdmd.databinding.FragmentMapBinding;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Polygon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class GeofenceAdapter extends ContextWrapper {
    private PendingIntent pendingIntent;
    private static FragmentMapBinding fragmentMapBinding;

    public void setFragmentMapBinding(FragmentMapBinding fragmentMapBinding) {
        this.fragmentMapBinding = fragmentMapBinding;
    }

    public GeofenceAdapter(Context base) {
        super(base);
    }

    public GeofencingRequest getGeofencingRequest(Geofence geofence) {
        return new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();
    }

    public GeofencingRequest getGeofencingRequest(List<Geofence> geofences) {
        return new GeofencingRequest.Builder()
                .addGeofences(geofences)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();
    }

    public Geofence getGeofence(String id, GeoPoint geoPoint, float radius, int transitionTypes) {
        return new Geofence.Builder()
                .setRequestId(id)
                .setCircularRegion(geoPoint.getLatitude(), geoPoint.getLongitude(), radius)
                .setTransitionTypes(transitionTypes)
                .setLoiteringDelay(5000)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
    }

    public PendingIntent getPendingIntent(Pokemon pokemon) {
        if(pendingIntent != null) {
            return pendingIntent;
        }

        Intent intent = new Intent(this, MapAdapter.GeofencingBroadcastReceiver.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("Pokemon", pokemon);

        intent.putExtra("Bundle", bundle);
        pendingIntent =  PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
}
