package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements ConnectionReceiver.ReceiverListener {

  ImageView mainactivity_image;
  long timeToAwait = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    long start = System.currentTimeMillis();

    mainactivity_image = findViewById(R.id.mainactivity_image);
    String filename = "Images/Graphics/GirlAndMeditation.webp";
    try (InputStream inputStream = getApplicationContext().getAssets().open(filename)) {
      Drawable drawable = Drawable.createFromStream(inputStream, null);
      mainactivity_image.setImageDrawable(drawable);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // BEGINNING. Here im going to place all of the code that may have an impact on app's
    // performance.
    SharedPreferences preferences = getSharedPreferences("app_data", 0);

    boolean isThereAnyProblemWithNetwork = checkConnection();
    // End of code block.

    long finish = System.currentTimeMillis();
    long timeSpent = finish - start;

    if (timeSpent < 2000) {
      timeToAwait = 2000 - timeSpent;
    }

    if (!isThereAnyProblemWithNetwork) {
      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          String value = preferences.getString("usage_state", null);
          if (value == null) {
            // the key does not exist
            startActivity(new Intent(getApplicationContext(), Onboarding.class));
          } else {
            // handle the value
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
          }
          finish();
        }
      }, timeToAwait);
    }
  }

  private boolean checkConnection() {

    // initialize intent filter
    IntentFilter intentFilter = new IntentFilter();

    // add action
    intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

    // register receiver
    registerReceiver(new ConnectionReceiver(), intentFilter);

    // Initialize listener
    ConnectionReceiver.Listener = this;

    // Initialize connectivity manager
    ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE);

    // Initialize network info
    NetworkInfo networkInfo = manager.getActiveNetworkInfo();

    // get connection status
    boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

    // check for problems
    return problemHandler(isConnected);
  }

  private boolean problemHandler(boolean isConnected) {

    // initialize color and message
    String message;
    int color;

    // check condition
    if (isConnected) {
      return false;
      // everything is fine! Skip.

    } else {
      startActivity(new Intent(getApplicationContext(), NoConnection.class).putExtra("reasonState",
          "no_connection"));
      finish();
      return true;
    }
  }

  @Override
  public void onNetworkChange(boolean isConnected) {
    // display snack bar
    problemHandler(isConnected);
  }

  @Override
  protected void onResume() {
    super.onResume();
    // call method
    checkConnection();
  }

  @Override
  protected void onPause() {
    super.onPause();
    // call method
    checkConnection();
  }
}
