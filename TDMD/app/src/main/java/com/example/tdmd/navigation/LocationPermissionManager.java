package com.example.tdmd.navigation;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationPermissionManager {
    private static final String[] PERMISSION_ARRAY = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    //TODO Decision to be made about which value of REQUESTCODE
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

    public boolean checkIfPermissionGranted(Activity activity) {
        //Log.d("PERMISSIONS", "Background permission boolean: " + (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED));
        //If one of the permissions is not granted return false as a whole
        for (String permission : PERMISSION_ARRAY) {
            if (!(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }
}

