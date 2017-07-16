package com.acbelter.weatherapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import timber.log.Timber;

public class WeatherUpdateScheduler {
    private static final boolean DEBUG_ALARM_MANAGER = false;

    public static void restartWeatherUpdates(Context context, int newUpdateInterval) {
        if (newUpdateInterval <= 0) {
            throw new IllegalArgumentException("Update interval must be > 0");
        }

        Timber.d("Restart weather updates");
        enableWeatherUpdateReceiver(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WeatherUpdateReceiver.class);
        PendingIntent alarmIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Cancel previous alarm
        alarmManager.cancel(alarmIntent);

        int newUpdateIntervalMs;
        if (DEBUG_ALARM_MANAGER) {
            newUpdateIntervalMs = 60000;
        } else {
            newUpdateIntervalMs = newUpdateInterval * 60 * 60 * 1000;
        }
        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                newUpdateIntervalMs,
                alarmIntent);
    }

    public static void stopWeatherUpdates(Context context) {
        Timber.d("Stop weather updates");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WeatherUpdateReceiver.class);
        PendingIntent alarmIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Cancel previous alarm
        alarmManager.cancel(alarmIntent);

        disableWeatherUpdateReceiver(context);
    }

    private static void enableWeatherUpdateReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, WeatherUpdateReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private static void disableWeatherUpdateReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, WeatherUpdateReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
