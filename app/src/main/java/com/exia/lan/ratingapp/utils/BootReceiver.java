package com.exia.lan.ratingapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.exia.lan.ratingapp.view.SplashActivity;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, SplashActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }

}
