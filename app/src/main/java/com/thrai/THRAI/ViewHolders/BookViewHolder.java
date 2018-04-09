package com.thrai.THRAI.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thrai.THRAI.ModelClasses.MedicalBook;
import com.thrai.THRAI.R;

/**
 * Created by fahim on 4/8/18.
 */
public class BookViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mContentView;
    public MedicalBook mItem;

    public BookViewHolder(View view) {
        super(view);
        mView = view;
        mContentView = (TextView) view.findViewById(R.id.content);
    }
}