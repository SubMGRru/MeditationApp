package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MeditationActivity extends AppCompatActivity {

  Context context;
  MediaPlayer mediaPlayer;
  boolean mediaPlayer_isPaused = false;
  int mediaPlayer_lengthBeforePause = 0;
  Button mediaPlayer_stateToggleButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_meditation);

    context = getApplicationContext();

    mediaPlayer_stateToggleButton = findViewById(R.id.meditations_mediastatetoggle);

    WebView webView = findViewById(R.id.meditationactivity_wvbox);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webView.addJavascriptInterface(new AuthExternalInterface(this), "Android");
    webView.loadUrl("https://test.deqstudio.com/medit/bg/bg1/");

    String url = "https://test.deqstudio.com/medit/get_audio.php"; // your URL here
    mediaPlayer = new MediaPlayer();
    mediaPlayer.setAudioAttributes(
        new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA).build());
    try {
      mediaPlayer.setDataSource(url);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      mediaPlayer.prepare(); // might take long! (for buffering, etc)
    } catch (IOException e) {
      e.printStackTrace();
    }
    mediaPlayer.start();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mediaPlayer.stop();
    mediaPlayer.release();
    finish();
  }

  public void media_stateToggle(View view) {
    if (mediaPlayer_isPaused == false) {
      mediaPlayer_isPaused = true;
      mediaPlayer.pause();
      mediaPlayer_lengthBeforePause = mediaPlayer.getCurrentPosition();
      mediaPlayer_stateToggleButton.setText(R.string.meditations_resumebutton);
    } else {
      mediaPlayer_isPaused = false;
      mediaPlayer.start();
      mediaPlayer.seekTo((int) (mediaPlayer_lengthBeforePause - 0.3 * 1000));
      mediaPlayer_stateToggleButton.setText(R.string.meditations_pausebutton);
    }
  }
}
