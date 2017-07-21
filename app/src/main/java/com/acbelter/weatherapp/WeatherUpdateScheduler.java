package com.acbelter.weatherapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class WeatherUpdateScheduler {
    private static final boolean DEBUG_ALARM_MANAGER = true;
    private static final long DEBUG_DELAY = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);

    public static void startWeatherUpdates(Context context, int newUpdateInterval, boolean restart) {
        if (newUpdateInterval <= 0) {
            throw new IllegalArgumentException("Update interval must be > 0");
        }

        Timber.d("Start weather updates: %s", restart);
        enableWeatherUpdateReceiver(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WeatherUpdateReceiver.class);
        PendingIntent updateIntent =
                PendingIntent.getBroadcast(context, 0, intent,
                        restart ? PendingIntent.FLAG_UPDATE_CURRENT : 0);
        // Cancel previous alarm
        alarmManager.cancel(updateIntent);

        long newUpdateIntervalMs;
        if (DEBUG_ALARM_MANAGER) {
            newUpdateIntervalMs = DEBUG_DELAY;
        } else {
            newUpdateIntervalMs = TimeUnit.HOURS.convert(newUpdateInterval, TimeUnit.MILLISECONDS);
        }
        alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + newUpdateIntervalMs,
                newUpdateIntervalMs,
                updateIntent);
    }

    public static void stopWeatherUpdates(Context context) {
        Timber.d("Stop weather updates");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WeatherUpdateReceiver.class);
        PendingIntent updateIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        // Cancel previous alarm
        alarmManager.cancel(updateIntent);

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
