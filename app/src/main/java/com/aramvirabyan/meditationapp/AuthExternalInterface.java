package com.aramvirabyan.meditationapp;

import static com.aramvirabyan.meditationapp.AuthService.save_AuthData;

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
    public void completeAuth(String token){
        save_AuthData(mContext, token);
    }
}
