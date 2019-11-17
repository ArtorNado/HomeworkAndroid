package com.example.myapplication.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.image.view.*

class ViewPagerAdapter(val context: Context?, val imageList: List<Int>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = imageList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(R.layout.image, container, false)
        layout.iv_image.setImageResource(imageList[position])
        container.addView(layout, 0)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}