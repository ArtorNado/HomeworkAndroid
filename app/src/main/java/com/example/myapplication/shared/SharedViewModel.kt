package com.example.myapplication.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.music_list.MusicData

class SharedViewModel : ViewModel() {


    val destinationFrom: MutableLiveData<MusicData> by lazy { MutableLiveData<MusicData>() }

    fun select(musicData: MusicData?) {
        destinationFrom.postValue(musicData)
    }
}
