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

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(CityData item);
    }

    private List<CityData> mLocations;
    private OnItemClickListener mClickListener;

    public CityAdapter(OnItemClickListener clickListener) {
        mLocations = new ArrayList<>();
        mClickListener = clickListener;

        setHasStableIds(true);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        CityViewHolder viewHolder = new CityViewHolder(view);

        view.setOnClickListener(it -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION)
                itemClicked(adapterPosition);
        });
        return viewHolder;
    }

    private void itemClicked(int position) {
        mClickListener.onItemClick(mLocations.get(position));
    }

    @Override
    public void onBindViewHolder(CityViewHolder viewHolder, int position) {
        CityData location = mLocations.get(position);
        viewHolder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public void update(List<CityData> list) {
        mLocations = list;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return mLocations.get(position).hashCode();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCity)
        TextView tvCity;

        public CityViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final CityData item) {
            tvCity.setText(item.getCityName());
        }
    }
}
