package com.example.myapplication.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_view.view.*
import kotlinx.android.synthetic.main.template.view.tv_description

class MusicViewHolder(
    override val containerView: View,
    val context: Context?
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val tv_title = containerView.tv_title
    val tv_description = containerView.tv_description
    val viewPager = containerView.viewPager

    fun bind(music: Music) {
        val imageList: List<Int> = arrayListOf(
            R.drawable.uvozh,
            R.drawable.uvozh
        )
        tv_description.text = music.description
        tv_title.text = music.title
        viewPager.adapter = ViewPagerAdapter(context, imageList)
    }

    companion object {

        fun create(parent: ViewGroup, context: Context?) =
            MusicViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false),
                context = context
            )
    }
}