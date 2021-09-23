package com.hizkeel.tredditapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.hizkeel.tredditapp.R;

public class MoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);



        WebView simpleWebView=(WebView) findViewById(R.id.webView);

        simpleWebView.loadUrl(getIntent().getExtras().getString("url_more"));
    }
}