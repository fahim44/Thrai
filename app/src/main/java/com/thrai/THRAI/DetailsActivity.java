package com.thrai.THRAI;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.thrai.THRAI.ModelClasses.Plant;

import java.util.HashSet;
import java.util.Iterator;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;

    private Plant data;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = (Plant) getIntent().getSerializableExtra("data");

      //  toolbar.setTitle(data.getPlant_name());

        makeCollapsingToolbarLayoutLooksGood((CollapsingToolbarLayout) findViewById(R.id.toolbar_layout));
        setTexts();
        initializeImageSlider();


    }

    private void setTexts(){
        setTitle(data.getPlant_name());
        TextView tv = findViewById(R.id.content);
        tv.setText(data.getString());
    }

    private void initializeImageSlider(){
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        //HashMap<Integer,String> url_maps = new HashMap<>();
        HashSet<String> url_maps = new HashSet<>();

        for(int i =0;i< data.getImages().size();i++){
            //url_maps.put(i,data.getImages().get(i));
            url_maps.add(data.getImages().get(i));
        }


        /*for(int i : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(url_maps.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",i);

            mDemoSlider.addSlider(textSliderView);
        }*/
        Iterator<String> iterator = url_maps.iterator();
        while (iterator.hasNext()){
            String st = iterator.next();
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(st)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",st);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new CustomSliderAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDemoSlider.startAutoCycle();
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        showZoomImage(slider.getBundle().getString("extra"));
        mDemoSlider.startAutoCycle();
    }


    private void showZoomImage(String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.image_zoom_layout,null);
        PhotoView iv = (PhotoView) vi.findViewById(R.id.iv_dialog_imgView);

        Picasso.with(this)
                .load(url)
                .into(iv);

        //PhotoViewAttacher mAttacher = new PhotoViewAttacher(iv);
        builder.setView(vi);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
       // mAttacher.update();
    }

    private void makeCollapsingToolbarLayoutLooksGood(CollapsingToolbarLayout collapsingToolbarLayout) {
        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/thsarabun.ttf");
        collapsingToolbarLayout.setCollapsedTitleTypeface(tf);
        collapsingToolbarLayout.setExpandedTitleTypeface(tf);
    }
}
