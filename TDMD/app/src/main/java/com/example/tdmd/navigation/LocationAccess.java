package com.example.tdmd.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import androidx.lifecycle.MutableLiveData;

public class LocationAccess {
    private LocationManager locationManager;
    private MutableLiveData<Location> mLocation;
    private Activity activity;

    public LocationAccess(Activity activity) {
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        mLocation = new MutableLiveData<>();
    }

    @SuppressLint("MissingPermission")
    public void GetLocation() {

        LocationPermissionManager locationPermissionsManager = new LocationPermissionManager();

        if(!locationPermissionsManager.checkIfPermissionGranted(activity)) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, location -> mLocation.postValue(location));
    }

    public MutableLiveData<Location> GetmLocation() {
        return mLocation;
    }
}
