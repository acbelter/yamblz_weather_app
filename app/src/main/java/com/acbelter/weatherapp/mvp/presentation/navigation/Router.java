package com.acbelter.weatherapp.mvp.presentation.navigation;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.view.about.InfoFragment;
import com.acbelter.weatherapp.mvp.view.search.SearchFragment;
import com.acbelter.weatherapp.mvp.view.settings.SettingsFragment;
import com.acbelter.weatherapp.mvp.view.weather.WeatherFragment;
import com.acbelter.weatherapp.mvp.view.weather.details.DetailFragment;

public class Router {

    private Activity activity;

    public Router(Activity activity) {
        this.activity = activity;
    }

    public void addWeatherFragment() {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(WeatherFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = WeatherFragment.newInstance();
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, WeatherFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    public void replaceWeatherFragment() {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(WeatherFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = WeatherFragment.newInstance();
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, WeatherFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showDetailsFragment(int position) {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(DetailFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = DetailFragment.newInstance(position);
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, DetailFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showSearchFragment() {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(SearchFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = SearchFragment.newInstance();
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, SearchFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            ((FragmentActivity) activity).getSupportFragmentManager().executePendingTransactions();
        }
    }

    public void showSettingsFragment() {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(SettingsFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = SettingsFragment.newInstance();
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, SettingsFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showInfoFragment() {
        Fragment fragment = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag(InfoFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = InfoFragment.newInstance();
            ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, InfoFragment.class.getSimpleName())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
