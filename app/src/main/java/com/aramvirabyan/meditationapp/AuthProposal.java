package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AuthProposal extends AppCompatActivity {

    ImageView authproposal_image;
    TextView authproposal_title;
    TextView authproposal_subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_proposal);

        authproposal_title = findViewById(R.id.authproposal_title);
        authproposal_subtitle = findViewById(R.id.authproposal_subtitle);

        authproposal_image = findViewById(R.id.authproposal_image);
        String filename = "Images/Icons/3d-fluency-globe.webp";
        try(InputStream inputStream = getApplicationContext().getAssets().open(filename)){
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            authproposal_image.setImageDrawable(drawable);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        ObjectAnimator moveUpY = ObjectAnimator.ofFloat(authproposal_image, "translationY", -529);
        moveUpY.setDuration(800);
        AnimatorSet moveUp = new AnimatorSet();
        moveUp.play(moveUpY);
        moveUp.start();

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(authproposal_image, "scaleX", 2.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(authproposal_image, "scaleY", 2.0f);
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();

        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();

        authproposal_title.setVisibility(View.GONE);
        authproposal_subtitle.setVisibility(View.GONE);

        TranslateAnimation animate_forTitle = new TranslateAnimation(0, authproposal_title.getWidth(),0,0);
        animate_forTitle.setDuration(3500);
        animate_forTitle.setFillAfter(true);
        authproposal_title.startAnimation(animate_forTitle);
        authproposal_title.setVisibility(View.VISIBLE);

        TranslateAnimation animate_forSubtitle = new TranslateAnimation(0, authproposal_subtitle.getWidth(),0,0);
        animate_forSubtitle.setDuration(3500);
        animate_forSubtitle.setFillAfter(true);
        authproposal_subtitle.startAnimation(animate_forSubtitle);
        authproposal_subtitle.setVisibility(View.VISIBLE);
    }

    public void call_mainAuth(android.view.View viewBox){
        AuthService.mainAuth(this);
    }
    public void call_anonAuth(android.view.View viewBox){
        AuthService.anonymousAuth(this, viewBox);
    }
}