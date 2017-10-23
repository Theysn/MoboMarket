package com.BestApp.VideoFree;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class EmaAct extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youremail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);

        interstitialAds = new InterstitialAd(this);
        interstitialAds.setAdUnitId(getResources().getString(R.string.admob_intersitials));

        interstitialAds.loadAd(adRequest);
        showInterstitial();

        //arabic font
        Typeface font_start = Typeface.createFromAsset(getAssets(), "mobily.ttf");
        //facebook font
        Typeface font_fb = Typeface.createFromAsset(getAssets(), "letter-faces.ttf");

        TextView fb = (TextView) findViewById(R.id.fb);
        fb.setTypeface(font_fb);

        EditText login = (EditText) findViewById(R.id.editText);
        login.setTypeface(font_start);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Button start = (Button) findViewById(R.id.button_start);
        start.setTypeface(font_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText login = (EditText) findViewById(R.id.editText);
                String sUsername = login.getText().toString().trim();
                v.startAnimation(animAlpha);
                if (sUsername.matches("")) {
                    Toast.makeText(EmaAct.this, getResources().getString(R.string.namevic), Toast.LENGTH_LONG).show();
                    return; }
                else {
                Intent i = new Intent(EmaAct.this, AttAct.class);
                startActivity(i); }

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
