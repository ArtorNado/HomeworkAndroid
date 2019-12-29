package com.example.myapplication.music_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
        private var music: List<MusicData>,
        private val clickLambda: (MusicData) -> Unit
) : RecyclerView.Adapter<MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
            MusicViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = music.size


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(music[position])
    }

}
