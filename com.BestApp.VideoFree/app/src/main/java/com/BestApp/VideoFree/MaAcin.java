package com.BestApp.VideoFree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import se.simbio.encryption.Encryption;


public class MaAcin extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        AdView adView = (AdView)this.findViewById(R.id.adView);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        interstitialAds = new InterstitialAd(this);
        interstitialAds.setAdUnitId(getResources().getString(R.string.admob_intersitials));
        AdRequest adRequest2 = new AdRequest.Builder()
                .build();
        interstitialAds.loadAd(adRequest2);
            showInterstitial();





        //arabic font
        Typeface font_start = Typeface.createFromAsset(getAssets(), "mobily.ttf");
        //facebook font
        Typeface font_fb = Typeface.createFromAsset(getAssets(), "letter-faces.ttf");

        TextView fb = (TextView) findViewById(R.id.fb);
        fb.setTypeface(font_fb);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Button start = (Button) findViewById(R.id.button_start);
        start.setTypeface(font_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    v.startAnimation(animAlpha);
                    Intent i = new Intent(MaAcin.this, MPAct.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(MaAcin.this,getResources().getString(R.string.connection), Toast.LENGTH_LONG ).show();
                }
            }

        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
