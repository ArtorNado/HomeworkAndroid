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

    private val tv_author = containerView.tv_author
    private val tv_description = containerView.tv_description
    private val viewPager = containerView.viewPager
    private val iv_photo = containerView.iv_photo
    private val indicator = containerView.indicator

    fun bind(music: Music) {

        tv_description.text = music.description
        tv_author.text = music.author
        iv_photo.setImageResource(music.avatar)
        val map = MusicRepository.getImageList()
        val imageList: List<Int> = map.getValue(music.author)
        viewPager.adapter = ViewPagerAdapter(context, imageList)
        indicator.setViewPager(viewPager)

    }

    companion object {

        fun create(parent: ViewGroup, context: Context?) =
                MusicViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false),
                        context = context
                )
    }
}
