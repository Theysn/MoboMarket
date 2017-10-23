package com.example.mac.viewpagerguide

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.Image
import android.support.constraint.ConstraintLayout
import android.support.v4.view.PagerAdapter
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.util.zip.Inflater
import android.text.method.ScrollingMovementMethod



/**
 * Created by mac on 02/10/2017.
 */
class viewPagerAdapter: PagerAdapter {

    var context: Context
    var path: ArrayList<String>
    lateinit var inflator: LayoutInflater
    constructor(context: Context, path: ArrayList<String>): super() {
        this.context = context
        this.path = path
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return  view == `object`
    }

    override fun getCount(): Int {

        return path.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rv: View = inflator!!.inflate(R.layout.viewpager_item,container, false)
        val txtView = rv.findViewById<TextView>(R.id.textView)
        txtView.setMovementMethod(ScrollingMovementMethod())
        txtView.text = path[position]
        container!!.addView(rv)
        return  rv
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as LinearLayout)
    }


}