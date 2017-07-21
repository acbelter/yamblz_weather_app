package com.acbelter.weatherapp.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import com.acbelter.weatherapp.BuildConfig;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.presentation.AboutPresenter;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends MvpAppCompatActivity implements AboutView {
    @InjectPresenter
    AboutPresenter mPresenter;
    @BindView(R.id.version_text)
    TextView mVersionText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        getWindow().setBackgroundDrawable(null);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mVersionText.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                close();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onBackPressed() {
        close();
    }

    @Override
    public void close() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
