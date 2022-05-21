package com.aramvirabyan.meditationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class PreferencesManagerInterface extends PreferencesManager {
    Context mContext;

    PreferencesManagerInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface public void updateService(String toast) {
        mContext.startActivity(new Intent(mContext, HomeActivity.class));
        ((Activity)mContext).finish();
    }

}
