package com.BestApp.VideoFree;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import se.simbio.encryption.Encryption;


public class AttAct extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AdView adView = (AdView)this.findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);


        interstitialAds = new InterstitialAd(this);
        interstitialAds.setAdUnitId(getResources().getString(R.string.admob_intersitials));

        interstitialAds.loadAd(adRequest);
        showInterstitial();

        Typeface font = Typeface.createFromAsset(getAssets(), "mobily.ttf");

        TextView age = (TextView) findViewById(R.id.age);
        age.setTypeface(font);

        CircularProgressBar circularProgressBar = (CircularProgressBar)findViewById(R.id.CircularProgressbar);
        int animationDuration = 120000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(100, animationDuration);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(AttAct.this, Stac.class);
                        startActivity(i);
                    }
                }, 120000);


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
