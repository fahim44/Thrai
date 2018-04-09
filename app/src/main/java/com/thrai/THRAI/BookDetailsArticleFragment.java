package com.thrai.THRAI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrai.THRAI.Adapters.BookDetailsViewPagerAdapter;
import com.veinhorn.scrollgalleryview.HackyViewPager;

import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
public class BookDetailsArticleFragment extends Fragment {

    private List<String> articles;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_detail_article, container, false);
        HackyViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new BookDetailsViewPagerAdapter(getChildFragmentManager(), articles));

        return view;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }
}
