package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MeditationActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        context = getApplicationContext();

        WebView webView = findViewById(R.id.meditationactivity_wvbox);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AuthExternalInterface(this), "Android");
        webView.loadUrl("https://deqstudio.com/");
    }
}