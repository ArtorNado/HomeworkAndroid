package com.example.myapplication.music_list

import com.example.myapplication.R

object MusicRepository {

    fun getMusicList(): ArrayList<MusicData> = arrayListOf(
            MusicData("May Wave", "1000", R.drawable.markul1, R.raw.song1),
            MusicData("Макс Корж", "Not", R.drawable.korz, R.raw.song2)
    )

}

