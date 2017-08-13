package com.acbelter.weatherapp.mvp.view.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.MainActivityPresenter;
import com.acbelter.weatherapp.mvp.presentation.navigation.Router;
import com.acbelter.weatherapp.mvp.view.activity.adapter.FavoritesCitiesAdapter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements DrawerLocker
        , MainActivityView
        , FavoritesCitiesAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.rvFavoritesCities)
    RecyclerView recyclerView;
    @BindView(R.id.rvSettings)
    RelativeLayout rvSettings;
    @BindView(R.id.rvInfo)
    RelativeLayout rvInfo;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.rvEdit)
    RelativeLayout rvEdit;
    @BindView(R.id.etSearchOnHeader)
    EditText etSearch;

    @Nullable
    private ActionBarDrawerToggle toggle;

    @Inject
    MainActivityPresenter presenter;

    @Nullable
    private FavoritesCitiesAdapter adapter;

    private Router router;

    private boolean twoPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();

        App.getInstance().plusActivityComponent().inject(this);
        setAdapter();
        presenter.onAttach(this);

        this.router = new Router(this);
        this.twoPain = false;
        if (findViewById(R.id.detail_fragment_container) != null) {
            twoPain = true;
        }

        if (savedInstanceState == null) {
            router.addWeatherFragment();
        }

        rvSettings.setOnClickListener(view -> {
            router.showSettingsFragment(twoPain);
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        rvInfo.setOnClickListener(view -> {
            router.showInfoFragment();
            if (!twoPain)
                drawerLayout.closeDrawer(GravityCompat.START);
        });
        rvEdit.setOnClickListener(view -> {
            if (adapter != null) {
                if (adapter.isShowDeleteButton())
                    tvEdit.setTypeface(null, Typeface.NORMAL);
                else
                    tvEdit.setTypeface(null, Typeface.BOLD);
                adapter.showDeleteButton(!adapter.isShowDeleteButton());
            }
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
                router.showSearchFragment();
                if (!twoPain)
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
        if (toggle != null)
            toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (toggle != null)
            toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetach();
        App.getInstance().releaseActivityComponent();
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
    public void setDrawerEnable(boolean enabled) {
        if (enabled)
            setDrawerUnlocked();
        else
            setDrawerLocked();
    }

    private void setDrawerLocked() {
        if (!twoPain) {
            if (toggle != null)
                toggle.setDrawerIndicatorEnabled(false);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toolbar.setNavigationIcon(R.drawable.ic_close);
        }
    }

    private void setDrawerUnlocked() {
        if (toggle != null)
            toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void showCityList(List<CityData> cities) {
        Timber.v("SIZE = " + cities.size());
        if (cities.isEmpty()) {
            router.showSearchFragment();
        } else {
            router.replaceWeatherFragment();
            if (adapter != null)
                adapter.update(cities);
        }
    }

    @Override
    public void showSearchFragment() {
        router.showSearchFragment();
    }

    @Override
    public void onCityItemClick(CityData item) {
        presenter.showSelectedWeather(item);
        if (!twoPain)
            drawerLayout.closeDrawer(GravityCompat.START);
        router.replaceWeatherFragment();
    }

    @Override
    public void deleteCityItem(CityData item) {
        presenter.deleteItem(item);
        tvEdit.setTypeface(null, Typeface.NORMAL);
        if (adapter != null)
            adapter.showDeleteButton(!adapter.isShowDeleteButton());
    }
}
