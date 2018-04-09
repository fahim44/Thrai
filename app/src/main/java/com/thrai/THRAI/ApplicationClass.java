package com.thrai.THRAI;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by fahim on 3/20/18.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/thsarabun.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
