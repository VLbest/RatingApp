package com.exia.lan.ratingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.exia.lan.ratingapp.R;

import java.util.Observable;

public class ThanksActivity extends AbstractActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks_page);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(ThanksActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }

    @Override
    public void update(Observable observable, Object data) {

    }

    @Override
    public void onClick(View v) {

    }
}
