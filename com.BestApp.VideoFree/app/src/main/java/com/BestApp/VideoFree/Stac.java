package com.BestApp.VideoFree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import se.simbio.encryption.Encryption;


public class Stac extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playstore_app);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        interstitialAds = new InterstitialAd(this);
        interstitialAds.setAdUnitId(getResources().getString(R.string.admob_intersitials));

        showInterstitial();

        TextView text = (TextView) findViewById(R.id.age);
        TextView text2 = (TextView) findViewById(R.id.age2);
        Typeface font = Typeface.createFromAsset(getAssets(), "mobily.ttf");

        text.setTypeface(font);
        text2.setTypeface(font);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        ImageButton appstore = (ImageButton) findViewById(R.id.store);
        appstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animAlpha);
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.BestApp.VideoFree"));
                    startActivity(i);
                    showInterstitial();
                }
                catch (Exception e)
                {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.BestApp.VideoFree"));
                    startActivity(i);
                    showInterstitial();
                }
            }
        });

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        appstore.startAnimation(shake);


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
