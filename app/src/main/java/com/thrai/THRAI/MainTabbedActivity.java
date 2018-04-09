package com.thrai.THRAI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thrai.THRAI.Connection.LocalVegReq;
import com.thrai.THRAI.Connection.LocalVegRes;
import com.thrai.THRAI.Connection.Response;
import com.thrai.THRAI.Connection.ServerHandlerAsyncTask;
import com.thrai.THRAI.Connection.ServerInterface;
import com.thrai.THRAI.Connection.ThaiHerbReq;
import com.thrai.THRAI.Connection.ThaiHerbRes;
import com.thrai.THRAI.Interfaces.OnListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.Plant;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainTabbedActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private int page_type;

    private AlertDialog loadingDialog = null;

    private PlantListFragment orderByAlphabet;
    private PlantSectionListFragment orderByTags;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed);

        page_type = getIntent().getIntExtra("page",1);



        orderByAlphabet = new PlantListFragment();
        orderByTags = new PlantSectionListFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(page_type==1)
            toolbar.setTitle("Local Vegetable");
        else if(page_type==2)
            toolbar.setTitle("Thai herb");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        changeTabsFont(tabLayout);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        serverHandler();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(Plant item) {
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("data",item);
        startActivity(intent);
    }

    private void serverHandler(){
        if(page_type==1){
            showLoadingView();
         new ServerHandlerAsyncTask(this, new LocalVegReq(), new LocalVegRes(), new ServerInterface() {
             @Override
             public void onResponse(Response res) {
                 LocalVegRes rsp = (LocalVegRes) res;
                 orderByAlphabet.setPlants(rsp.getVegitables());
                 orderByAlphabet.updateList();

                 orderByTags.setAll_tags(rsp.getAll_tags());
                 orderByTags.setPlants(rsp.getVegitables());
                 orderByTags.updateList();

                 hideLoadingView();
             }
         }).execute();
        }
        else if(page_type==2){
            showLoadingView();
            new ServerHandlerAsyncTask(this, new ThaiHerbReq(), new ThaiHerbRes(), new ServerInterface() {
                @Override
                public void onResponse(Response res) {
                    ThaiHerbRes rsp = (ThaiHerbRes) res;
                    orderByAlphabet.setPlants(rsp.getHerbs());
                    orderByAlphabet.updateList();

                    orderByTags.setAll_tags(rsp.getAll_tags());
                    orderByTags.setPlants(rsp.getHerbs());
                    orderByTags.updateList();

                    hideLoadingView();
                }
            }).execute();
        }

    }

    private void showLoadingView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.loading_view_layout,null);

        builder.setView(vi);
        loadingDialog = builder.create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loadingDialog.show();
    }

    private void hideLoadingView(){
        if(loadingDialog != null){
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    private void changeTabsFont(TabLayout tabLayout) {
        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/thsarabun.ttf");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            //return PlantListFragment.newInstance(1);
            if(position==0)
                return orderByAlphabet;
            return orderByTags;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
