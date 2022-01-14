package com.example.tdmd.Map;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import org.osmdroid.util.GeoPoint;

public class MyLocationListener implements LocationListener {
    private GeoPoint geoPoint;
    public void onLocationChanged(Location location) {
        geoPoint = new GeoPoint(location);
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}