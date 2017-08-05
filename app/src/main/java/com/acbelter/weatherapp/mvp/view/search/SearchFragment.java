package com.acbelter.weatherapp.mvp.view.search;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.SearchPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;
import com.acbelter.weatherapp.mvp.view.search.adapter.CityAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class SearchFragment extends BaseFragment implements SearchView, CityAdapter.OnItemClickListener {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvCityList)
    RecyclerView recyclerView;
    @BindView(R.id.content_layout)
    ConstraintLayout constraintLayout;

    @Inject
    SearchPresenter presenter;

    private CityAdapter adapter;

    private static final long TYPING_DELAY = 400;

    private Unbinder unbinder;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    private void initAdapter() {
        adapter = new CityAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        presenter.onAttach(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        showKeyboard();
        initAdapter();

        RxTextView.textChanges(etSearch)
                .debounce(TYPING_DELAY, TimeUnit.MILLISECONDS)
                .filter(charSequence -> charSequence.length() > 2)
                .subscribe(charSequence ->
                        presenter.showCityList(charSequence.toString()));
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void updateCityList(List<CityData> locations) {
        adapter.update(locations);
    }

    @Override
    public void showError() {
        Snackbar errorSnackbar = Snackbar.make(constraintLayout, R.string.get_city_list_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    @Override
    public void close() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }

    @Override
    public void onStop() {
        super.onStop();

        hideKeyboard();
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
    protected void setTitle() {
        getActivity().setTitle(R.string.search_title);
    }

    @Override
    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(false);
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public void onItemClick(CityData item) {
        presenter.saveSelectedCityAndWeather(item);
    }
}
