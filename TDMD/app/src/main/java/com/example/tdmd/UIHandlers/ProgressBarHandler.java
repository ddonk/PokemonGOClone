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
    private Context context;

    public ProgressBarHandler(Context context) {
        this.context = context;
    }

    public void SetProgressbarValue(int value, ProgressBar progressBar) {
        double barPercentage = percentageOfBar(value, progressBar);

        if(barPercentage > GREENCOLORMAXPERC || barPercentage < 0) {
            //Log.d("BAR", "%: " + barPercentage + " Thus returning");
            return;
        }

        if(barPercentage <= REDCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.PBRED), android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar red");
            return;
        }

        if(barPercentage > REDCOLORMAXPERC && barPercentage <= YELLOWCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.PBYELLOW), android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar yellow");
            return;
        }

        if(barPercentage > YELLOWCOLORMAXPERC && barPercentage <= GREENCOLORMAXPERC) {
            progressBar.setProgress(value);
            progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.PBGREEN), android.graphics.PorterDuff.Mode.SRC_IN);
            //Log.d("BAR", "%: " + barPercentage + " Thus made progressbar green");
            return;
        }

    }

    public double percentageOfBar(double value, ProgressBar progressBar) {
        return value/progressBar.getMax();
    }
}
