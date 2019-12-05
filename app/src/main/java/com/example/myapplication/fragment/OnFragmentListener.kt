package com.example.myapplication.fragment

import com.example.myapplication.music_list.MusicData

interface OnFragmentListener {
    fun onFragmentListener(comand: String)
    fun OnFragmentListener(comand: String, musicData: MusicData)
}
