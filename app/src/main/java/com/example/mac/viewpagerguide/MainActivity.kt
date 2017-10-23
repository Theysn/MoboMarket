package com.example.mac.viewpagerguide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import android.content.Intent
import android.net.Uri
import android.util.Log


class MainActivity() : AppCompatActivity() {


    lateinit var mPager: ViewPager
    var path = ArrayList<String>()
    lateinit var interstitialAd: InterstitialAd
    lateinit var adView: AdView
    lateinit var adRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = resources
        val name = res.getStringArray(R.array.Strings)

        path.addAll(name)


        adRequest = AdRequest.Builder().build()
        adView = findViewById(R.id.adView)
        adView.loadAd(adRequest)

        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = getString(R.string.admob_interstitial)

        interstitialAd.loadAd(adRequest)


        interstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()

                var adRequest2 = AdRequest.Builder().build()
                interstitialAd.loadAd(adRequest2)

            }
        }

        mPager = findViewById(R.id.viewPager)
        var adapter: PagerAdapter = viewPagerAdapter(this, path)
        mPager.adapter = adapter

        mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {


            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {
                showInterstitial()

            }
        })

        var nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                mPager.setCurrentItem(mPager.currentItem+1, true)
                showInterstitial()
            }
        })

        var backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

                mPager.setCurrentItem(mPager.currentItem-1, true)
                showInterstitial()
            }

        })

        var reviewButton = findViewById<Button>(R.id.reviewButton)
        reviewButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {


                try {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))

                    startActivity(i)

                } catch (e: Exception) {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
                    startActivity(i)


                }

            }

        })




    }
    var counter: Int = 0
    fun showInterstitial() {

    if (counter == 2) {
        if (interstitialAd.isLoaded) {
            interstitialAd.show()
        }
        counter = 0
    }
    else {
            counter += 1
        }

    }
}
