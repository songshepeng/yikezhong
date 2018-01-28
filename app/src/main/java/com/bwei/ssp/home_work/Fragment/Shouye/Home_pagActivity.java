package com.bwei.ssp.home_work.Fragment.Shouye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.bwei.ssp.home_work.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Home_pagActivity extends AppCompatActivity {

    @InjectView(R.id.web)
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pag);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        web.loadUrl(url);
    }
}
