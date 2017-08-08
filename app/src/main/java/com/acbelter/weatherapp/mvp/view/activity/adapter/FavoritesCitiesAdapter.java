package com.acbelter.weatherapp.mvp.view.activity.adapter;

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

public class FavoritesCitiesAdapter extends RecyclerView.Adapter<FavoritesCitiesAdapter.FavoritesCitiesViewHolder> {

    private List<CityData> favoritesCities;

    public FavoritesCitiesAdapter() {
        this.favoritesCities = new ArrayList<>();

        setHasStableIds(true);
    }

    @Override
    public FavoritesCitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_city_item, parent, false);
        return new FavoritesCitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesCitiesViewHolder viewHolder, int position) {
        CityData city = favoritesCities.get(position);
        viewHolder.bind(city);
    }

    @Override
    public int getItemCount() {
        return favoritesCities.size();
    }

    @Override
    public long getItemId(int position) {
        return favoritesCities.get(position).hashCode();
    }


    public void update(List<CityData> cities) {
        favoritesCities = cities;
        notifyDataSetChanged();
    }

    public static class FavoritesCitiesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFavoriteCity)
        TextView tvCity;

        public FavoritesCitiesViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final CityData item) {
            tvCity.setText(item.getShortName());
        }
    }
}
