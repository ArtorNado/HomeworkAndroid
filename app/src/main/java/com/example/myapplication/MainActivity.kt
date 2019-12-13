package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.fragment.OnFragmentListener
import com.example.myapplication.fragment.TrackFragment
import com.example.myapplication.fragment.authors.AuthorFragment
import com.example.myapplication.fragment.authors.ChangeFragmentListener
import com.example.myapplication.music_list.*
import com.example.myapplication.shared.SharedViewModel

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity(), OnFragmentListener, ChangeFragmentListener {

    private var service: MusicService? = null
    private var mBound = false
    var model: SharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        addStartFragment()
        bind()
    }

    override fun onResume() {
        super.onResume()
        this.model?.select(NotificationChanges.musicData)
    }

    override fun onFragmentListener(comand: String) {
        when (comand) {
            Constants.ACTION.NEXT_TRACK -> nextTrack()
            Constants.ACTION.PAUSE -> pause()
            Constants.ACTION.PREV_TRACK -> previousTrack()
        }
    }

    override fun onFragmentListener(comand: String, musicData: MusicData) {
        when (comand) {
            Constants.ACTION.START -> replaceData(musicData)
        }
    }

    override fun changeFragment(containerViewId: Int, instance: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(containerViewId, instance)
            commit()
        }
    }

    //////////////////////////////////////////////////////
    /////////////////////CONSTANTS////////////////////////
    //////////////////////////////////////////////////////
    private fun addStartFragment() {
        val argument: Bundle = intent.extras
        val fragmentName = argument.getString("Fragment")
        Log.d("FRAGMENT_NAME", fragmentName)
        when (fragmentName) {
            "music_list" -> supportFragmentManager.beginTransaction().apply {
                val author = argument.getString("author")
                if (author.equals("all")) {
                    replace(R.id.host_fragment, MusicListFragment.newInstance(author))
                    commit()
                }
            }
            "track_info" -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.host_fragment, TrackFragment.newInstance())
                commit()
            }
            "author_list" -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.host_fragment, AuthorFragment.newInstance())
                commit()
            }
        }
    }

    private fun replaceData(musicData: MusicData) {
        model?.select(musicData)
        service?.startPlay(musicData)
    }

    private fun nextTrack() {
        model?.select(service?.nextTrack())
    }

    private fun previousTrack() {
        model?.select(service?.previousTrack())
    }

    private fun pause() {
        service?.pause()
    }

    private fun bind() {
        if (!mBound) {
            val intent = Intent(this, MusicService::class.java)
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
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

}
