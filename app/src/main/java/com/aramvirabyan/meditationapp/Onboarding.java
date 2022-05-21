package com.aramvirabyan.meditationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Onboarding extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private Drawable bg_slide1_img;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
        fragmentTransaction.commit();

        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override public void onRightOut() {
                startActivity(new Intent(getApplicationContext(), AuthProposal.class));
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage(getString(R.string.onboarding_slide1_title), getString(R.string.onboarding_slide1_text), Color.parseColor("#d0bcff"), R.drawable.relaxing_at_home_amico, R.drawable.icons8_moon_and_stars_96);
        PaperOnboardingPage scr2 = new PaperOnboardingPage(getString(R.string.onboarding_slide2_title), getString(R.string.onboarding_slide2_text), Color.parseColor("#efb8c8"), R.drawable.reading_girl_and_cats, R.drawable.icons8_cat_96);
        PaperOnboardingPage scr3 = new PaperOnboardingPage(getString(R.string.onboarding_slide3_title), getString(R.string.onboarding_slide3_text), Color.parseColor("#ffd8e4"), R.drawable.casual_life_3d_woman_taking_a_funny_photo_with_dog, R.drawable.freepic_icon_rocket);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}