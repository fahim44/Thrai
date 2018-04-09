package com.thrai.THRAI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veinhorn.scrollgalleryview.Constants;
import com.veinhorn.scrollgalleryview.HackyViewPager;

/**
 * Created by fahim on 4/9/18.
 */
public class ViewPagerItemFragment extends Fragment {

    private String contant;

    private HackyViewPager viewPager;

    public void setContant(String contant) {
        this.contant = contant;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.content_details, container, false);
        TextView tv = rootView.findViewById(R.id.content);

        viewPager = (HackyViewPager) getActivity().findViewById(com.veinhorn.scrollgalleryview.R.id.viewPager);

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(Constants.IS_LOCKED, false);
            viewPager.setLocked(isLocked);
        }

        if(contant!=null)
            tv.setText(contant);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(Constants.IS_LOCKED, viewPager.isLocked());
        }
        super.onSaveInstanceState(outState);
    }

    private boolean isViewPagerActive() {
        return viewPager != null;
    }
}
