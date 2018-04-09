package com.thrai.THRAI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thrai.THRAI.ModelClasses.Plant;
import com.thrai.THRAI.R;

/**
 * Created by aiubian on 3/14/18.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mContentView;
    public Plant mItem;

    public ViewHolder(View view) {
        super(view);
        mView = view;
        mContentView = (TextView) view.findViewById(R.id.content);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}