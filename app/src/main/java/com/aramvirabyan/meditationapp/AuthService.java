package com.aramvirabyan.meditationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthService {

    public static String new_auth_token;

    public static void anonymousAuth(Context context, android.view.View viewBox) {

        RequestQueue queue = Volley.newRequestQueue(context);
        Gson g = new Gson();

        Snackbar snackbar = Snackbar.make(viewBox, context.getString(R.string.snackbar_connectionAwait), Snackbar.LENGTH_INDEFINITE);
        snackbar.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, context.getString(R.string.api_server) + "/service/auth_anonymousAccess",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            new_auth_token = jsonObject.getString("auth_token");
                            //Toast.makeText(context, "Result: " + new_auth_token, Toast.LENGTH_LONG).show();
                            save_AuthData(context, new_auth_token);
                            snackbar.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                snackbar.dismiss();
                if(error instanceof TimeoutError || error instanceof ServerError || error instanceof ParseError ){
                    context.startActivity(new Intent(context, NoConnection.class).putExtra("reasonState", "unavailable_server"));
                    ((Activity)context).finish();
                } else if(error instanceof NetworkError || error instanceof NoConnectionError){
                    context.startActivity(new Intent(context, NoConnection.class).putExtra("reasonState", "no_connection"));
                    ((Activity)context).finish();
                } else{
                    Toast.makeText(context, "We have an error: " + error, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static void mainAuth(Context context){
        context.startActivity(new Intent(context, AuthExternal.class));
    }

    public static void save_AuthData(Context context, String token){
        SharedPreferences preferences = context.getSharedPreferences("app_data", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("auth_token", token);
        editor.apply();

        context.startActivity(new Intent(context, PreferencesManager.class));
        ((Activity)context).finish();
    }
}
