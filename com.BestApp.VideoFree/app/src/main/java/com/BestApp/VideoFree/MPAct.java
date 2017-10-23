package com.BestApp.VideoFree;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.io.InputStream;


public class MPAct extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;

    Drawable drawable;
    String linkapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4ormp3);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
        interstitialAds = new InterstitialAd(this);
        interstitialAds.setAdUnitId(getResources().getString(R.string.admob_intersitials));

        showInterstitial();

        TextView age = (TextView) findViewById(R.id.age);
    Typeface font = Typeface.createFromAsset(getAssets(), "mobily.ttf");
    age.setTypeface(font);
    Button yes = (Button) findViewById(R.id.button_yes);
    Button no = (Button) findViewById(R.id.button_no);
    yes.setTypeface(font);
    no.setTypeface(font);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
    yes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(animAlpha);
            Intent i = new Intent(MPAct.this, HostAct.class);
            startActivity(i);
            showInterstitial();
        }
    });
    no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent i1 = new Intent(MPAct.this, HostAct.class);
                startActivity(i1);
                showInterstitial();
            }
    });



    }

    public void showInterstitial() {
        interstitialAds.setAdListener(new AdListener() {

            public void onAdLoaded() {

                if (interstitialAds.isLoaded()) {
                    interstitialAds.show();
                }

            }
        });
    }



}
