package com.exia.lan.ratingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.exia.lan.ratingapp.R;
import com.exia.lan.ratingapp.model.SplashModel;

import java.util.Observable;

public class SplashActivity extends AbstractActivity {


    private SplashModel Model;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        this.initComponents();

        //  ********************************************
        // TODO Move it away from here
        // TODO Add a fade in / fade out animation
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, this.Model.getSPLASH_TIME_OUT());


        //  ********************************************

    }

    private void initComponents(){
        this.Model = new SplashModel();
        this.Model.addObserver(this);

    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {

    }

}
