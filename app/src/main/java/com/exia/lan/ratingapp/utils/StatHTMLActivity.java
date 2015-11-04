package com.exia.lan.ratingapp.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.exia.lan.ratingapp.R;

import butterknife.ButterKnife;

public class StatHTMLActivity extends AppCompatActivity implements I_StatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = (WebView) findViewById(R.id.stat_webview);
        webview.loadUrl("file:///");
        setContentView(webview);
    }

}
