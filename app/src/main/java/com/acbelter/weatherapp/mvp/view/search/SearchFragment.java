package com.acbelter.weatherapp.mvp.view.search;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.SearchPresenter;
import com.acbelter.weatherapp.mvp.view.search.adapter.CityAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchFragment extends MvpAppCompatFragment implements SearchView, CityAdapter.OnItemClickListener {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvCityList)
    RecyclerView recyclerView;
    @BindView(R.id.content_layout)
    ConstraintLayout constraintLayout;

    @Inject
    @InjectPresenter
    SearchPresenter presenter;

    private CityAdapter mAdapter;

    private static final long TYPING_DELAY = 400;

    private Unbinder unbinder;

    @ProvidePresenter
    public SearchPresenter provideSearchPresenter() {
        return presenter;
    }


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().plusActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    private void initAdapter() {
        mAdapter = new CityAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        initAdapter();

        RxTextView.textChanges(etSearch)
                .debounce(TYPING_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(charSequence ->
                        presenter.showCityList(charSequence.toString()));
    }


    @Override
    public void updateCityList(List<CityData> locations) {
        mAdapter.update(locations);
    }

    @Override
    public void showError() {
        Snackbar errorSnackbar =
                Snackbar.make(constraintLayout, R.string.get_city_list_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    @Override
    public void close() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onStop() {
        super.onStop();

        App.getInstance().releaseActivityComponent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDetach();
    }

    @Override
    public void onItemClick(CityData item) {
        presenter.saveSelectedCityAndWeather(item);
    }
}
