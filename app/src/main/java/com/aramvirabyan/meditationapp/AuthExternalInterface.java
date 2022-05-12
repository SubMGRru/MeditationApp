package com.aramvirabyan.meditationapp;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class AuthExternalInterface extends AuthExternal{
    Context mContext;

    AuthExternalInterface(Context c){
        mContext = c;
    }

    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        //mContext.startActivity(new Intent(mContext, HomeScreen.class));
    }

    @JavascriptInterface
    public void init__SettingsCenter(){
        //mContext.startActivity(new Intent(mContext, SettingsCenter.class));
    }
}
