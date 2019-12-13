package com.example.myapplication.repository

import com.example.myapplication.R
import com.example.myapplication.music_list.MusicData

object MusicRepository {

    fun getMusicList(): ArrayList<MusicData> = arrayListOf(
            MusicData("May Wave", "1000", "May Wave", R.drawable.may1, R.raw.song1),
            MusicData("Макс Корж", "Single", "Макс Корж", R.drawable.korz, R.raw.song2),
            MusicData("Руки Вверх", "Конец попсе, танцуют все", "Руки Вверх", R.drawable.handsup, R.raw.handsuprack1),
            MusicData("Big Baby Tape", "Single", "Big Baby Tape", R.drawable.tape, R.raw.kisstape),
            MusicData("Движение", "Уход 3: Спасение", "May Wave$", R.drawable.dviz, R.raw.dviztrack)
    )
}
