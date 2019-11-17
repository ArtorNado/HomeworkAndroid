package com.example.myapplication.ui.notifications

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private var music: ArrayList<Music>,
    val context: Context?
) : RecyclerView.Adapter<MusicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder.create(parent, context)


    override fun getItemCount(): Int = music.size


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(music[position])
    }

}