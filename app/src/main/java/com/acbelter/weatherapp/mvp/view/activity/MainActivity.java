package com.acbelter.weatherapp.mvp.view.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.view.about.InfoFragment;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.search.SearchFragment;
import com.acbelter.weatherapp.mvp.view.settings.SettingsFragment;
import com.acbelter.weatherapp.mvp.view.weather.WeatherFragment;
import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, DrawerLocker {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView nvDrawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawerContent(nvDrawer);

        if (savedInstanceState == null) {
            WeatherFragment weatherFragment = WeatherFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame, weatherFragment, WeatherFragment.class.getSimpleName()).commit();
        }

        initSearchEdittext();

    }

    private void initSearchEdittext() {
        View header = nvDrawer.getHeaderView(0);
        EditText etSearch = (EditText) header.findViewById(R.id.etSearchOnHeader);

        etSearch.setOnTouchListener((view, motionEvent) -> {
            if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                showFragment(SearchFragment.class);
                drawerLayout.closeDrawers();
            }

            return true;
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        setupToolbar();
        navigationView.setNavigationItemSelectedListener(item -> {
            selectDrawerItem(item);
            return true;
        });
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(view -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                setDrawerUnlocked();
            }
            Timber.v("click");
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            setDrawerUnlocked();
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectDrawerItem(item);
        return true;
    }

    void selectDrawerItem(MenuItem menuItem) {
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_view:
                fragmentClass = WeatherFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.nav_info:
                fragmentClass = InfoFragment.class;
                break;
            default:
                fragmentClass = WeatherFragment.class;
        }

        showFragment(fragmentClass);

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void showFragment(Class fragmentClass) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment, fragmentClass.getSimpleName())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setDrawerEnable(boolean enabled) {
        if (enabled)
            setDrawerUnlocked();
        else
            setDrawerLocked();
    }

    private void setDrawerLocked() {
        toggle.setDrawerIndicatorEnabled(false);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setNavigationIcon(R.drawable.ic_close);
    }

    private void setDrawerUnlocked() {
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}
