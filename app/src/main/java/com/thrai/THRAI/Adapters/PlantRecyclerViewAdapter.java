package com.thrai.THRAI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrai.THRAI.Interfaces.OnListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.Plant;
import com.thrai.THRAI.R;
import com.thrai.THRAI.ViewHolders.ViewHolder;

import java.util.List;


public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Plant> mValues;
    private final OnListFragmentInteractionListener mListener;

    public PlantRecyclerViewAdapter(List<Plant> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getPlant_name());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
