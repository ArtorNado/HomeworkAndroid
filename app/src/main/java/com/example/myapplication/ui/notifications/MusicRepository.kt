package com.example.myapplication.ui.notifications

import com.example.myapplication.R

object MusicRepository {

    fun getDataSource(): ArrayList<Music> = arrayListOf(
            Music("MayWave", "Рэп", R.drawable.mayw),
            Music("Markul", "Хип-хоп", R.drawable.markul)
    )

    fun getImageList(): Map<String, ArrayList<Int>> = mapOf(
            "MayWave" to arrayListOf(R.drawable.may1, R.drawable.may2, R.drawable.mayw),
            "Markul" to arrayListOf(R.drawable.markul1, R.drawable.markul2, R.drawable.markul3, R.drawable.markul)
    )

}