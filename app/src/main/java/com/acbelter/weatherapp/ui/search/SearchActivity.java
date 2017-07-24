package com.acbelter.weatherapp.ui.search;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SearchActivity extends MvpAppCompatActivity implements SearchView, CityAdapter.OnItemClickListener {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvCityList)
    RecyclerView recyclerView;

    @Inject
    @InjectPresenter
    SearchPresenter mPresenter;

    private CityAdapter mAdapter;

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

        RxTextView.textChanges(etSearch)
                .subscribe(charSequence ->
                        mPresenter.showCityList(charSequence.toString()));
    }

    private void initAdapter() {
        mAdapter = new CityAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    public void close() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void updateCityList(List<CityData> locations) {
        mAdapter.update(locations);
    }

    @Override
    public void onStop() {
        super.onStop();

        mPresenter.stopGetCurrentWeatherProcess();
        App.getComponentManager().removeWeatherComponent();
    }

    @Override
    public void onItemClick(CityData item) {
        mPresenter.saveSelectedCity(item);
        mPresenter.closeActivity();
    }
}
