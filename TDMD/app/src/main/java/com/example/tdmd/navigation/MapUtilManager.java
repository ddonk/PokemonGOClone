package com.example.tdmd.navigation;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapUtilManager {
    private MapView mapView;
    //private View infoWindowView;

    public MapUtilManager(MapView mapView) {
        this.mapView = mapView;
        //this.infoWindowView = infoWindowView;
    }

    public void StartCurrentLocation() {
        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(mapView);
        locationNewOverlay.enableMyLocation();
        mapView.getOverlayManager().add(locationNewOverlay);
        mapView.invalidate();
    }
}
