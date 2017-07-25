package com.acbelter.weatherapp.ui.search;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.presentation.SearchPresenter;
import com.acbelter.weatherapp.ui.search.adapter.CityAdapter;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends MvpAppCompatActivity implements SearchView, CityAdapter.OnItemClickListener {

    @BindView(R.id.etSearch)
    EditText mEtSearch;
    @BindView(R.id.rvCityList)
    RecyclerView mRecyclerView;
    @BindView(R.id.content_layout)
    ConstraintLayout mConstraintLayout;

    @Inject
    @InjectPresenter
    SearchPresenter mPresenter;

    private CityAdapter mAdapter;

    private static final long TYPING_DELAY = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        initAdapter();

        RxTextView.textChanges(mEtSearch)
                .debounce(TYPING_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(charSequence ->
                        mPresenter.showCityList(charSequence.toString()));
    }

    private void initAdapter() {
        mAdapter = new CityAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @ProvidePresenter
    public SearchPresenter provideSearchPresenter() {
        return mPresenter;
    }

    @Override
    public void onBackPressed() {
        mPresenter.closeActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mPresenter.closeActivity();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void close() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void updateCityList(List<CityData> locations) {
        mAdapter.update(locations);
    }

    @Override
    public void showError() {
        Snackbar errorSnackbar =
                Snackbar.make(mConstraintLayout, R.string.get_city_list_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    @Override
    public void onStop() {
        super.onStop();

        App.getComponentManager().removeWeatherComponent();
    }

    @Override
    public void onItemClick(CityData item) {
        mPresenter.saveSelectedCityAndWeather(item);
    }
}
