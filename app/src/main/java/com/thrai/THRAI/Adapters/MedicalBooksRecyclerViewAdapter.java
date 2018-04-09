package com.thrai.THRAI.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrai.THRAI.Interfaces.OnBookListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.MedicalBook;
import com.thrai.THRAI.R;
import com.thrai.THRAI.ViewHolders.BookViewHolder;

import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
public class MedicalBooksRecyclerViewAdapter extends RecyclerView.Adapter<BookViewHolder>  {
    private final List<MedicalBook> mValues;
    private final OnBookListFragmentInteractionListener mListener;

    public MedicalBooksRecyclerViewAdapter(List<MedicalBook> items, OnBookListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plant, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getBook_name());

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
