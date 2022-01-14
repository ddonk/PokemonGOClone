package com.example.tdmd;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;

public class AskPermission {
    private static final String[] PERMISSION_ARRAY = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
    };

    public static void AskPermission(Activity activity, @Nullable Callable callable) {
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_ARRAY, 1);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1);

            AskPermission(activity, callable);
        }  else {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
