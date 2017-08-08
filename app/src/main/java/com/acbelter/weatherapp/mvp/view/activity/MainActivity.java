package com.acbelter.weatherapp.mvp.view.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.MainActivityPresenter;
import com.acbelter.weatherapp.mvp.view.about.InfoFragment;
import com.acbelter.weatherapp.mvp.view.activity.adapter.FavoritesCitiesAdapter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.search.SearchFragment;
import com.acbelter.weatherapp.mvp.view.settings.SettingsFragment;
import com.acbelter.weatherapp.mvp.view.weather.WeatherFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerLocker
        , MainActivityView
        , FavoritesCitiesAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rvFavoritesCities)
    RecyclerView recyclerView;
    @BindView(R.id.tvSettings)
    TextView tvSettings;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.etSearchOnHeader)
    EditText etSearch;

    private ActionBarDrawerToggle toggle;

    @Inject
    MainActivityPresenter presenter;

    private FavoritesCitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();

        App.getInstance().plusActivityComponent().inject(this);
        setAdapter();
        presenter.onAttach(this);

        if (savedInstanceState == null) {
            WeatherFragment weatherFragment = WeatherFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_frame, weatherFragment, WeatherFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }

        tvSettings.setOnClickListener(view -> {
            showFragment(SettingsFragment.class);
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        tvInfo.setOnClickListener(view -> {
            showFragment(InfoFragment.class);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        initSearchEdittext();
    }

    private void setAdapter() {
        adapter = new FavoritesCitiesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initSearchEdittext() {
        etSearch.setOnTouchListener((view, motionEvent) -> {
            if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                showFragment(SearchFragment.class);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });
    }

    private void setupToolbar() {
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_SETTLING && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    presenter.showCityList();
                }
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(view -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
                setDrawerUnlocked();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.showCityList();
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
    public void onStop() {
        super.onStop();
        presenter.onDetach();
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

    @Override
    public void showCityList(List<CityData> cities) {
        if (cities.isEmpty()) {
            clearBackStack();
            showFragment(SearchFragment.class);
        } else {
            showFragment(WeatherFragment.class);
            adapter.update(cities);
        }
    }

    @Override
    public void showWeather() {
        clearBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, WeatherFragment.newInstance(), WeatherFragment.class.getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void clearBackStack() {
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onItemClick(CityData item) {
        presenter.showSelectedWeather(item);
    }
}
