package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView mainactivity_image;
    long timeToAwait = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long start = System.currentTimeMillis();

        mainactivity_image = findViewById(R.id.mainactivity_image);
        String filename = "Images/Graphics/GirlAndMeditation.webp";
        try(InputStream inputStream = getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            mainactivity_image.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //BEGINNING. Here im going to place all of the code that may have an impact on app's performance.
        SharedPreferences preferences = getSharedPreferences("app_data", 0);
        //End of code block.

        long finish = System.currentTimeMillis();
        long timeSpent = finish - start;

        if(timeSpent < 2000){
            timeToAwait = 2000 - timeSpent;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String value = preferences.getString("usage_state",null);
                if (value == null) {
                    // the key does not exist
                    startActivity(new Intent(getApplicationContext(), Onboarding.class));
                } else {
                    // handle the value
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        }, timeToAwait);
    }
}