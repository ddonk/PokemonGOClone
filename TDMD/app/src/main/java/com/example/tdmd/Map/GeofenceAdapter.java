package com.example.tdmd.Map;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeofenceAdapter extends ContextWrapper {
    private PendingIntent pendingIntent;

    public GeofenceAdapter(Context base) {
        super(base);
    }

    public GeofencingRequest getGeofencingRequest(Geofence geofence) {
        return new GeofencingRequest.Builder()
                .addGeofence(geofence)
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

    public PendingIntent getPendingIntent() {
        if(pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(this, GeofencingBroadcastReceiver.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Pokemon", new Pokemon("Treecko", Collections.singletonList(Type.Grass), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png"));
        intent.putExtra("Bundle", bundle);
        pendingIntent =  PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
}
