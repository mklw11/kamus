package com.example.kamus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class DeveloperActivity extends AppCompatActivity {

    WebView webview, webview2;
    String url = "file:///android_asset/developer.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        webview = (WebView) findViewById(R.id.webView1);

        webview.loadUrl(url);
    }
}
