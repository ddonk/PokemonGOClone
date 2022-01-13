package com.example.tdmd.Map;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import org.osmdroid.util.GeoPoint;

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
        pendingIntent =  PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
}
