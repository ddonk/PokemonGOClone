package com.example.tdmd.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.tdmd.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapAdapter {
    public void InitMap(MapView map, Activity activity) {
        Configuration.getInstance().load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(20);
        SetMapLocation(activity, (MapController) mapController);

        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(map);
        locationOverlay.enableMyLocation();
        map.getOverlays().add(locationOverlay);
    }

    private void SetMapLocation(Activity activity, MapController mapController) {
        if(checkIfPermissionGranted(activity)) {
            Location location = GetLocation((LocationManager) activity.getSystemService(Context.LOCATION_SERVICE));
            mapController.setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));
        } else {
            AskPermission((Activity) activity);
            SetMapLocation(activity, mapController);
        }
    }

    private static final String[] PERMISSION_ARRAY = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public boolean checkIfPermissionGranted(Activity activity) {
        for (String permission : PERMISSION_ARRAY) {
            if (!(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private static final int REQUESTCODE = 1;

    public void AskPermission(Activity activity) {
        if (checkIfPermissionGranted(activity)) {
            //TODO Add method when permission is granted
            Toast toast = Toast.makeText(activity, "Permissions were granted", Toast.LENGTH_LONG);
            toast.show();
        } else {
            ActivityCompat.requestPermissions(activity, PERMISSION_ARRAY, REQUESTCODE);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUESTCODE);
        }
    }

    @SuppressLint("MissingPermission")
    public Location GetLocation(LocationManager locationManager) {
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }
}
