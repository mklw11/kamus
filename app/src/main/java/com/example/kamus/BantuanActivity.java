package com.example.kamus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class BantuanActivity extends AppCompatActivity {

    WebView webview;
    String url = "file:///android_asset/bantuan.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        webview = (WebView) findViewById(R.id.webView1);

        webview.loadUrl(url);
    }
}
