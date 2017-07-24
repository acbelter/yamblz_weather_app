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

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        CityData location = mLocations.get(position);
        viewHolder.bind(location, mClickListener);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCity)
        TextView tvCity;

        public MyViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final CityData item, final OnItemClickListener clickListener) {
            tvCity.setText(item.getCityName());
            itemView.setOnClickListener(view -> clickListener.onItemClick(item));
        }
    }
}
