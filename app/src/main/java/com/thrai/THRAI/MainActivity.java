package com.thrai.THRAI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClick(View v) {
        if(v.getId()==R.id.btn_localVegetable){
            Intent intent = new Intent(this,MainTabbedActivity.class);
            intent.putExtra("page",1);
            startActivity(intent);
        }
        else if(v.getId()==R.id.btn_herb){
            Intent intent = new Intent(this,MainTabbedActivity.class);
            intent.putExtra("page",2);
            startActivity(intent);
        }
        else if(v.getId()==R.id.btn_textbook){
            Intent intent = new Intent(this,MedicalTextbookActivity.class);
            startActivity(intent);
        }
    }
}
