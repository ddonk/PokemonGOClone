package com.example.tdmd.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tdmd.Adapters.RESTAPIAdapter;
import com.example.tdmd.AskPermission;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.SharedPreferencesManager;
import com.example.tdmd.VolleyCallback;
import com.example.tdmd.databinding.FragmentMapBinding;
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
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
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
    private static FragmentMapBinding binding;

    public MapAdapter(Activity activity, FragmentMapBinding binding) {
        this.binding = binding;
        MapInit(binding.mapview, activity);

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
        if (location != null) {
            yourLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        }

        displayMyCurrentLocationOverlay();

        this.geofencingClient = LocationServices.getGeofencingClient(this.activity);
        this.geofenceAdapter = new GeofenceAdapter(this.activity);
        this.geofenceAdapter.setFragmentMapBinding(binding);

        binding.mvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Geofencing", "Adding pokemon via click");

                RESTAPIAdapter.GetPokemon(activity, new VolleyCallback() {
                    @Override
                    public void OnSucces(Pokemon result) {
                        GeoPoint geoPoint = GetRandomLocation(yourLocation, 4);
                        AddGeofence(geoPoint, result, 30);
                    }
                });
            }
        });
    }

    private void displayMyCurrentLocationOverlay() {
        MyLocationNewOverlay locationNewOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(activity), mapView);
        locationNewOverlay.enableMyLocation();
        locationNewOverlay.enableFollowLocation();

        mapView.getOverlayManager().add(locationNewOverlay);

        mapView.getController().setCenter(yourLocation);
    }

    public Marker AddPokemonMarkerToMap(GeoPoint geoPoint, Pokemon pokemon) {
        Marker marker = new Marker(this.mapView);
        marker.setId(pokemon.getName());
        marker.setPosition(geoPoint);
        marker.setIcon(new BitmapDrawable(GetBitmapFromUrl(pokemon)));

        mapView.getOverlayManager().add(marker);

        return marker;
    }

    public Bitmap GetBitmapFromUrl(Pokemon pokemon) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        InputStream inputStream = null;
        try {
            inputStream = new URL(pokemon.getImageurl()).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), 200, 200, false);
    }

    @SuppressLint("MissingPermission")
    public Geofence AddGeofence(GeoPoint geoPoint, Pokemon pokemon, float radius) {
        Geofence geofence = geofenceAdapter.getGeofence(pokemon.getName(), geoPoint, radius, Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceAdapter.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceAdapter.getPendingIntent(pokemon);

        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Log.d("Geofencing", "Succes Add");

                        //DrawCircle(geoPoint, radius);
                        AddPokemonMarkerToMap(geoPoint, pokemon);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Geofencing", "Failure Add: " + e);

                    }
                });

        return geofence;
    }

    public Polygon DrawCircle(GeoPoint center, double radiusInMeters) {
        List<GeoPoint> circlePoints = Polygon.pointsAsCircle(center, radiusInMeters);
        Polygon circle = new Polygon(mapView);
        circle.setPoints(circlePoints);
        mapView.getOverlayManager().add(circle);
        mapView.invalidate();
        return circle;
    }

    private GeoPoint GetRandomLocation(GeoPoint currentLocation, int bounds) {
        float randomX = getRandomNumber(-bounds, bounds);
        float randomY = getRandomNumber(-bounds, bounds);

        randomX /= 10000;
        randomY /= 10000;

        return new GeoPoint(currentLocation.getLatitude() + randomX, currentLocation.getLongitude() + randomY);
    }

    private float getRandomNumber(int min, int max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static class GeofencingBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Geofencing", "Triggering");
            Toast.makeText(context, "Geofencing triggered...", Toast.LENGTH_LONG).show();
            Bundle bundle = intent.getBundleExtra("Bundle");
            Pokemon pokemon = (Pokemon) bundle.getSerializable("Pokemon");
            Log.d("Geofencing", pokemon.toString());
            binding.mvButton2.setVisibility(View.VISIBLE);
            binding.mvButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Geofencing", "Testing button");
                    SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(binding.getRoot().getContext());
                    ArrayList<Pokemon> pokemons = sharedPreferencesManager.GetPokemon();
                    pokemons.add(pokemon);
                    sharedPreferencesManager.AddPokemon(pokemons);
                    Log.d("Geofencing", sharedPreferencesManager.GetPokemon().toString());
                    binding.mvButton2.setVisibility(View.INVISIBLE);

                    for (int i = 0; i < binding.mapview.getOverlays().size(); i++) {
                        Overlay overlay = binding.mapview.getOverlays().get(i);
                        if (overlay instanceof Marker && ((Marker) overlay).getId().equals(pokemon.getName())) {
                            binding.mapview.getOverlays().remove(overlay);
                        }
                    }
                }
            });
        }
    }
}
