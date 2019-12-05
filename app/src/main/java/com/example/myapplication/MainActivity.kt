package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.fragment.OnFragmentListener
import com.example.myapplication.music_list.Change
import com.example.myapplication.music_list.MusicData
import com.example.myapplication.music_list.MusicListFragment
import com.example.myapplication.music_list.MusicService
import com.example.myapplication.shared.SharedViewModel

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity(), OnFragmentListener, Change {

    private var service: MusicService? = null
    private var mBound = false
    private var model = SharedViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        val argument: Bundle = intent.extras
        val fragmentName = argument.getString("Fragment")
        addStartFragment(fragmentName)
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        Log.d("NEXT", "PRESSED")
    }

    override fun onStart() {
        super.onStart()
    }

    fun addStartFragment(fragmentName: String) {
        when (fragmentName) {
            "music_list" -> supportFragmentManager.also {
                it.beginTransaction().apply {
                    replace(R.id.nav_host_fragmentt, MusicListFragment.newInstance(), "tag")
                    commit()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }
    }

    fun replaceData(musicData: MusicData) {
        model.select(musicData)
        service?.startPlay(musicData)
    }

    fun nextTrack() {
        service?.nextTrack()/*?.let { replaceData(it) }*/
    }


    fun previousTrack() {
        service?.previousTrack()?.let { replaceData(it) }
    }

    fun pause() {
        service?.pause()
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, serviceB: IBinder?) {
            val binder: MusicService.MusicBinder = serviceB as MusicService.MusicBinder
            service = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }
    }

    override fun onFragmentListener(comand: String) {
        when (comand) {
            "next" -> nextTrack()
            "pause" -> pause()
            "previous" -> previousTrack()
        }
    }

    override fun OnFragmentListener(comand: String, musicData: MusicData) {
        when (comand) {
            "start" -> replaceData(musicData)
        }
    }

    override fun change(musicData: MusicData) {
        model.select(musicData)
    }


}
