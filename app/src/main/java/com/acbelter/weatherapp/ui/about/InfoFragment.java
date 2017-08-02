package com.acbelter.weatherapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acbelter.weatherapp.BuildConfig;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.presentation.InfoPresenter;
import com.acbelter.weatherapp.ui.drawer.DrawerLocker;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends MvpAppCompatFragment implements InfoView {

    @InjectPresenter
    InfoPresenter presenter;
    @BindView(R.id.version_text)
    TextView versionText;

    private Unbinder unbinder;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        setTitle();
        setDrawableEnabled();
    }

    private void setTitle() {
        getActivity().setTitle(R.string.about);
    }

    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        presenter.showAppVersion();
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
    public void showAppVersion() {
        versionText.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    }
}
