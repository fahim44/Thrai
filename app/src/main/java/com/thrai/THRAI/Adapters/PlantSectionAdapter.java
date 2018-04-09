package com.thrai.THRAI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrai.THRAI.Interfaces.OnListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.Plant;
import com.thrai.THRAI.R;
import com.thrai.THRAI.ViewHolders.HeaderHolder;
import com.thrai.THRAI.ViewHolders.ViewHolder;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by aiubian on 3/14/18.
 */

public class PlantSectionAdapter extends StatelessSection {

    private List<Plant> mValues;
    private OnListFragmentInteractionListener mListener;
    private String header_text;

    public PlantSectionAdapter(String header,List<Plant> items, OnListFragmentInteractionListener listener) {
        super(SectionParameters.builder()
        .itemViewWillBeProvided()
        .headerViewWillBeProvided()
        .build());

        header_text = header;
        mValues = items;
        mListener = listener;
    }

    @Override
    public int getContentItemsTotal() {
        return mValues.size();
    }

    @Override
    public View getItemView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plant, parent, false);
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mItem = mValues.get(position);
        viewHolder.mContentView.setText(mValues.get(position).getPlant_name());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(viewHolder.mItem);
                }
            }
        });
    }

    @Override
    public View getHeaderView(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_header, parent, false);
        //return super.getHeaderView(parent);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        //super.onBindHeaderViewHolder(holder);
        HeaderHolder headerHolder = (HeaderHolder) holder;
        headerHolder.mContentView.setText(header_text);
    }
}
