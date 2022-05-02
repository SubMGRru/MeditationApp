package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView mainactivity_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainactivity_image = findViewById(R.id.mainactivity_image);
        String filename = "Images/Graphics/GirlAndMeditation.webp";
        try(InputStream inputStream = getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            mainactivity_image.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}