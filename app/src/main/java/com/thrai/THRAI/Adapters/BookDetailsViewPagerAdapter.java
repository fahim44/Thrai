package com.thrai.THRAI.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.thrai.THRAI.ViewPagerItemFragment;

import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
/*public class BookDetailsViewPagerAdapter extends PagerAdapter {

    private List<String> articles;
    private Context context;

    public BookDetailsViewPagerAdapter(Context context, List<String> articles){
        this.context = context;
        this.articles = articles;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_details,
                    container, false);

        TextView tv = layout.findViewById(R.id.content);
        tv.setText(articles.get(position));
        container.addView(layout);
        return layout;
    }



    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        boolean b = (view == object);
        return b;
    }
}*/

public class BookDetailsViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> articles;


    public BookDetailsViewPagerAdapter(FragmentManager fm, List<String> articles) {
        super(fm);
        this.articles = articles;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < articles.size()) {
            String st = articles.get(position);
            fragment = loadImageFragment(st);
        }
        return fragment;
    }

    private Fragment loadImageFragment(String st) {
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        fragment.setContant(st);
        return fragment;
    }

    @Override
    public int getCount() {
        return articles.size();
    }
}

