package com.acbelter.weatherapp.mvp.view.activity.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class FavoritesCitiesAdapter extends RecyclerView.Adapter<FavoritesCitiesAdapter.FavoritesCitiesViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CityData item);

        void deleteItem(CityData item);
    }

    @NonNull
    private List<CityData> favoritesCities;
    @NonNull
    private final OnItemClickListener itemClickListener;
    @NonNull
    private boolean isShow;

    public FavoritesCitiesAdapter(@NonNull OnItemClickListener clickListener) {
        this.favoritesCities = new ArrayList<>();
        this.itemClickListener = clickListener;
        this.isShow = false;
    }

    @Override
    public FavoritesCitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_city_item, parent, false);

        FavoritesCitiesViewHolder viewHolder = new FavoritesCitiesViewHolder(view);
        view.setOnClickListener(it -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                itemClicked(adapterPosition);
            }
        });

        viewHolder.ivDelete.setOnClickListener(delete -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                deleteItem(adapterPosition);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoritesCitiesViewHolder viewHolder, int position) {
        CityData city = favoritesCities.get(position);
        viewHolder.bind(city);
        viewHolder.showDeleteButton(isShow);
        if (position == 0) {
            viewHolder.setVisible();
        }
    }

    @Override
    public int getItemCount() {
        return favoritesCities.size();
    }

    private void itemClicked(int position) {
        itemClickListener.onItemClick(favoritesCities.get(position));
    }

    private void deleteItem(int position) {
        itemClickListener.deleteItem(favoritesCities.get(position));
    }

    public void update(@Nullable List<CityData> cities) {
        if (cities == null)
            return;
        favoritesCities = cities;
        notifyDataSetChanged();
    }

    public void showDeleteButton(@NonNull boolean show) {
        this.isShow = show;
        notifyDataSetChanged();
    }

    public
    @NonNull
    boolean isShowDeleteButton() {
        return isShow;
    }

    static class FavoritesCitiesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFavoriteCity)
        TextView tvCity;
        @BindView(R.id.ivFavoriteFlag)
        ImageView ivFavorite;
        @BindView(R.id.ivDelete)
        ImageView ivDelete;

        FavoritesCitiesViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bind(final CityData item) {
            tvCity.setText(item.getShortName());
        }

        void setVisible() {
            ivFavorite.setVisibility(VISIBLE);
            tvCity.setTypeface(null, Typeface.BOLD);
        }

        void showDeleteButton(boolean isShow) {
            if (isShow)
                ivDelete.setVisibility(VISIBLE);
            else
                ivDelete.setVisibility(INVISIBLE);
        }
    }
}
