package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AuthExternal extends AppCompatActivity {

  public Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth_external);

    context = getApplicationContext();

    WebView webView = findViewById(R.id.authexternal_wvbox);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webView.addJavascriptInterface(new AuthExternalInterface(this), "Android");
    webView.loadUrl(context.getString(R.string.staticRenderer_server) + "/auth/");
  }
}
