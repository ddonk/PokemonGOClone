package com.example.tdmd.UIHandlers;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.example.tdmd.R;

public class ProgressBarHandler {
    private final double REDCOLORMAXPERC = 0.33;
    private final double YELLOWCOLORMAXPERC = 0.67;
    private final double GREENCOLORMAXPERC = 1;

    private final int PBRED = Color.parseColor("#C32121");
    private final int PBYELLOW = Color.parseColor("#FFC107");
    private final int PBGREEN = Color.parseColor("#12B815");


    public void SetProgressbarValue(int value, ProgressBar progressBar) {
        double barPercentage = percentageOfBar(value, progressBar);

        if(barPercentage > GREENCOLORMAXPERC || barPercentage < 0) {
            //Log.d("BAR", "%: " + barPercentage + " Thus returning");
            return;
        }

        if(barPercentage <= REDCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(PBRED, android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar red");
            return;
        }

        if(barPercentage > REDCOLORMAXPERC && barPercentage <= YELLOWCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(PBYELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar yellow");
            return;
        }

        if(barPercentage > YELLOWCOLORMAXPERC && barPercentage <= GREENCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(PBGREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar green");
            return;
        }

    }

    public double percentageOfBar(double value, ProgressBar progressBar) {
        return value/progressBar.getMax();
    }
}
