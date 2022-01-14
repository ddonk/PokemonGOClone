package com.example.tdmd.Map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tdmd.AskPermission;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.Type;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class MapAdapter {
    private MapView mapView;
    private Activity activity;
    private MyLocationListener myLocationListener;
    private LocationManager locationManager;
    private GeoPoint yourLocation;
    private GeofencingClient geofencingClient;
    private GeofenceAdapter geofenceAdapter;

    public MapAdapter(MapView mapView, Activity activity) {
        MapInit(mapView, activity);

        AskPermission.AskPermission(activity, new Callable() {
            @Override
            public Object call() throws Exception {
                LocationInit();
                return null;
            }
        });
    }

    private void MapInit(MapView mapView, Activity activity) {
        this.activity = activity;
        this.mapView = mapView;
        this.myLocationListener = new MyLocationListener();

        Configuration.getInstance().load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        mapView.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = mapView.getController();
        mapController.setZoom(20);
    }

    @SuppressLint("MissingPermission")
    private void LocationInit() {
        locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationListener);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null ) {
            yourLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        }

        displayMyCurrentLocationOverlay();

        this.geofencingClient = LocationServices.getGeofencingClient(this.activity);
        this.geofenceAdapter = new GeofenceAdapter(this.activity);

        Pokemon pokemon = new Pokemon("Treecko", Collections.singletonList(Type.Grass), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/252.png");

        AddGeofence(new GeoPoint(51.5924, 4.7813), pokemon, 100);
    }

    private void displayMyCurrentLocationOverlay() {
        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(activity), mapView);
        locationNewOverlay.enableMyLocation();
        locationNewOverlay.enableFollowLocation();

        mapView.getOverlayManager().add(locationNewOverlay);

        mapView.getController().setCenter(yourLocation);
    }

    public void AddPokemonMarkerToMap(GeoPoint geoPoint, Pokemon pokemon) {
        OverlayItem overlayItem = new OverlayItem(pokemon.getName(), "Sample Description", geoPoint);

        new GetImageFromUrl(overlayItem).execute(pokemon.getImageurl());

        ArrayList items = new ArrayList<OverlayItem>();
        items.add(overlayItem);

        ItemizedIconOverlay mLocationOverlay = new ItemizedIconOverlay<OverlayItem>(activity, items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        this.mapView.getOverlays().add(mLocationOverlay);
    }

    @SuppressLint("MissingPermission")
    public void AddGeofence(GeoPoint geoPoint, Pokemon pokemon, float radius) {
        Geofence geofence = geofenceAdapter.getGeofence(pokemon.getName(), geoPoint, radius, Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceAdapter.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceAdapter.getPendingIntent();

        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Log.d("Geofencing", "Succes Add");
                        AddPokemonMarkerToMap(geoPoint, pokemon);
                        DrawCircle(geoPoint, radius);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Geofencing", "Failure Add: " + e);

                    }
                });
    }
    public void DrawCircle(GeoPoint center, double radiusInMeters) {
        List<GeoPoint> circlePoints = Polygon.pointsAsCircle(center, radiusInMeters);
        Polygon circle = new Polygon(mapView);
        circle.setPoints(circlePoints);
        mapView.getOverlayManager().add(circle);
        mapView.invalidate();
    }
}

class GetImageFromUrl extends AsyncTask<String, Void, Bitmap>{
    OverlayItem overlayItem;
    public GetImageFromUrl(OverlayItem img){
        this.overlayItem = img;
    }
    @Override
    protected Bitmap doInBackground(String... url) {
        String stringUrl = url[0];
        Bitmap bitmap = null;
        InputStream inputStream;
        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        overlayItem.setMarker(new BitmapDrawable(bitmap));
    }
}