package com.acbelter.weatherapp.mvp.view.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.mvp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.mvp.presentation.navigation.Router;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;
import com.acbelter.weatherapp.mvp.view.weather.adapter.WeatherAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class WeatherFragment extends BaseFragment implements WeatherView
        , SharedPreferences.OnSharedPreferenceChangeListener, WeatherAdapter.OnItemClickListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvWeather)
    RecyclerView recyclerView;

    @Inject
    WeatherPresenter presenter;

    @Inject
    SharedPreferences preferences;

    @Nullable
    private Unbinder unbinder;
    @Nullable
    private WeatherAdapter adapter;

    private Router router;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        presenter.onAttach(this);
        router = new Router(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.unbinder = ButterKnife.bind(this, view);
        setSwipeLayout();
        setAdapter();
        presenter.getWeather();
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void setAdapter() {
        this.adapter = new WeatherAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        this.recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.updateWeather());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getInstance().releaseActivityComponent();
        Timber.d("Remove weather component");
        if (unbinder != null)
            unbinder.unbind();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void setTitle() {
        getActivity().setTitle(R.string.weather);
    }

    @Override
    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(true);
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public void startLoading() {
        if (swipeRefreshLayout.isRefreshing()) {
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showWeather(@Nullable FullWeatherModel weatherData) {
        if (adapter != null)
            adapter.update(weatherData);
    }

    @Override
    public void showError() {
        Snackbar errorSnackbar =
                Snackbar.make(swipeRefreshLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        presenter.getWeather();
    }

    @Override
    public void onItemClick(int position) {
        boolean twoPain = false;
        if (getActivity().findViewById(R.id.detail_fragment_container) != null)
            twoPain = true;
        router.showDetailsFragment(position, twoPain);
    }
}
