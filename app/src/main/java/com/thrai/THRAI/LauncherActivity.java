package com.thrai.THRAI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by fahim on 3/20/18.
 */

public class LauncherActivity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View easySplashScreenView = new EasySplashScreen(this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(1500)
                .withBackgroundResource(R.drawable.flash)
               // .withHeaderText("Header")
              //  .withFooterText("Copyright 2016")
              //  .withBeforeLogoText("My cool company")
          //      .withLogo(R.mipmap.ic_launcher_round)
              //  .withAfterLogoText("Some more details")
                .create();

        setContentView(easySplashScreenView);
    }

    @Override
    public void onBackPressed() {

    }
}
