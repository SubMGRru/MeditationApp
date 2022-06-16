package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AuthExternal extends AppCompatActivity {

  public Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth_external);

    context = getApplicationContext();

    WebView webView = findViewById(R.id.authexternal_wvbox);
    webView.setWebViewClient(new WebViewClient());
    WebSettings webSettings = webView.getSettings();
    webSettings.setDomStorageEnabled(true);
    webSettings.setJavaScriptEnabled(true);
    webView.setBackgroundColor(Color.TRANSPARENT);
    webView.addJavascriptInterface(new AuthExternalInterface(this), "Android");
    webView.loadUrl(context.getString(R.string.staticRenderer_server)
        + "/auth/?source=androidapp&api_server=" + context.getString(R.string.api_server));
  }
}
