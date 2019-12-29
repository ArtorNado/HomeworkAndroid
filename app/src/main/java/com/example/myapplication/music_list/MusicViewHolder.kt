package com.example.myapplication.music_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.music_list_template.view.*

class MusicViewHolder(
        override val containerView: View,
        private val clickLambda: (MusicData) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val tv_title = containerView.tv_title
    private val tv_album = containerView.tv_album
    private val iv_cover = containerView.iv_cover

    fun bind(music: MusicData) {
        tv_title.text = music.title
        tv_album.text = music.album
        iv_cover.setImageResource(music.cover)
        itemView.setOnClickListener {
            clickLambda(music)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (MusicData) -> Unit) =
                MusicViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.music_list_template, parent, false),
                        clickLambda
                )
    }
}
