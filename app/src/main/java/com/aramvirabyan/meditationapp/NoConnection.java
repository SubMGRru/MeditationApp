package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class NoConnection extends AppCompatActivity {

    ImageView noconnection_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        String filename = "Images/Icons/wi-fi-disconnected.webp";
        String text_title = "Sorry,";
        String text_subtitle = "something is wrong.";
        TextView noconnection_text = findViewById(R.id.textView_title);
        TextView noconnection_subtext = findViewById(R.id.textView_subtitle);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.getString("reasonState").equals("no_connection")){
                filename = "Images/Icons/wi-fi-disconnected.webp";
                text_title = getResources().getString(R.string.noconnection_noconnection_title);
                text_subtitle = getResources().getString(R.string.noconnection_noconnection_subtitle);
            }else if(extras.getString("reasonState").equals("unavailable_server")){
                filename = "Images/Icons/cloud-cross.webp";
                text_title = getResources().getString(R.string.noconnection_unavailableserver_title);
                text_subtitle = getResources().getString(R.string.noconnection_unavailableserver_subtitle);
            }
        }else{
            text_title = "dick";
        }
        noconnection_image = findViewById(R.id.noconnection_image);
        try(InputStream inputStream = getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            noconnection_image.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        noconnection_text.setText(text_title);
        noconnection_subtext.setText(text_subtitle);
    }

    private void restartTheApp(boolean isConnected) {
        // handle button click with complete app's reload
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }
}