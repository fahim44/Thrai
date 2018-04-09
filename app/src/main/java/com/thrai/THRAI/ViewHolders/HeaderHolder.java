package com.thrai.THRAI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thrai.THRAI.R;

/**
 * Created by fahim on 3/15/18.
 */

public class HeaderHolder extends RecyclerView.ViewHolder  {
    public  View mView;
    public  TextView mContentView;

    public HeaderHolder(View view) {
        super(view);
        mView = view;
        mContentView = (TextView) view.findViewById(R.id.content);
    }
}
