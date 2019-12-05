package com.example.myapplication.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.music_list.MusicData

class SharedViewModel : ViewModel() {
    private val selected = MutableLiveData<MusicData>()

    fun select(musicData: MusicData) {
        Log.d("SELECTED", "POST")
        selected.postValue(musicData)
    }

    fun getSelected(): LiveData<MusicData> = selected
}
