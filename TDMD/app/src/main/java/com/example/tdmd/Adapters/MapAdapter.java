package com.example.tdmd.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tdmd.Contracts.Pokemon;
import com.squareup.picasso.Picasso;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MapAdapter {
    private Activity activity;
    private MapView mapView;

    public MapAdapter(Activity activity, MapView mapView) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy gfgPolicy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(gfgPolicy);
        }

        this.activity = activity;
        this.mapView = mapView;
    }

    public void InitMap() {
        Configuration.getInstance().load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        mapView.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = mapView.getController();
        mapController.setZoom(20);
        SetMapLocation((MapController) mapController);

        MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(mapView);
        locationOverlay.enableMyLocation();
        mapView.getOverlays().add(locationOverlay);
    }

    private void SetMapLocation(MapController mapController) {
        if(checkIfPermissionGranted()) {
            Location location = GetLocation((LocationManager) activity.getSystemService(Context.LOCATION_SERVICE));
            mapController.setCenter(new GeoPoint(location.getLatitude(), location.getLongitude()));
        } else {
            AskPermission();
            SetMapLocation(mapController);
        }
    }

    private static final String[] PERMISSION_ARRAY = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public boolean checkIfPermissionGranted() {
        for (String permission : PERMISSION_ARRAY) {
            if (!(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private static final int REQUESTCODE = 1;

    public void AskPermission() {
        if (checkIfPermissionGranted()) {
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

    public void AddPokemonToMap(GeoPoint geoPoint, Pokemon pokemon) {
        OverlayItem overlayItem = new OverlayItem(pokemon.getName(), "Sample Description", geoPoint);
        overlayItem.setMarker(new BitmapDrawable(GetBitmapFromURL(pokemon.getImageurl())));

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

    public Bitmap GetBitmapFromURL(String url) {
        try {
            URL _url = new URL(url);
            return BitmapFactory.decodeStream(_url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }

        return null;
    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 150, 150, false);
        return new BitmapDrawable(activity.getResources(), bitmapResized);
    }
}
