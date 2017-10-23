package com.BestApp.VideoFree;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HostAct extends Activity {
    private InterstitialAd interstitialAds;
    com.google.android.gms.ads.AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.activity_link);
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);


        //arabic font
        Typeface font_start = Typeface.createFromAsset(getAssets(), "mobily.ttf");
        //facebook font
        Typeface font_fb = Typeface.createFromAsset(getAssets(), "letter-faces.ttf");

        TextView fb = (TextView) findViewById(R.id.fb);
        fb.setTypeface(font_fb);

        EditText login = (EditText) findViewById(R.id.editText);
        login.setTypeface(font_start);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("mp3");
        list.add("mp4");
        list.add("Avi");
        list.add("MPEG");
        list.add("MKV");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
                    Toast.makeText(HostAct.this, getResources().getString(R.string.fb), Toast.LENGTH_LONG).show();
                    return; }


                else {

                    Intent i = new Intent (HostAct.this, EmaAct.class);
                    startActivity(i);
                     }
            }
        });






    }
   /* public boolean checkEmail(String email) {

        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
 */

}