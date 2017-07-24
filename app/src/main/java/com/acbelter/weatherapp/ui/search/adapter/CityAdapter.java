package com.acbelter.weatherapp.ui.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private List<CityData> locations;

    public CityAdapter() {
        this.locations = new ArrayList<>();

        setHasStableIds(true);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        CityData location = locations.get(position);
        viewHolder.tvCity.setText(location.getCityName());

        viewHolder.tvCity.setOnClickListener(view -> Timber.v("city = " + location.getLatitude()));
    }


    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void update(List<CityData> list) {
        locations = list;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return locations.get(position).hashCode();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCity)
        TextView tvCity;

        public MyViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
