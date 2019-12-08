package com.example.myapplication.music_list

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.myapplication.MainActivity


@Suppress("DEPRECATION")
@SuppressLint("Registered")
class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private var list = MusicRepository.getMusicList()
    private var musicNumber = 0
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder

    inner class MusicBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Constants.ACTION.NEXT_TRACK -> this.MusicBinder().getService().nextTrack()
            Constants.ACTION.PREV_TRACK -> this.MusicBinder().getService().previousTrack()
            Constants.ACTION.PAUSE -> this.MusicBinder().getService().pause()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        createPIntent()
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
        return MusicBinder()
    }

    fun startPlay(musicData: MusicData) {
        idetnifyNumSong(musicData)
        stopPlay()
        NotificationChanges.musicData = musicData
        mediaPlayer = MediaPlayer.create(applicationContext, musicData.music)
        mediaPlayer?.start()
    }

    fun stopPlay() {
        mediaPlayer?.stop()
    }

    fun nextTrack(): MusicData? {
        val musicData: MusicData
        if (list.size == musicNumber + 1) {
            musicNumber = 0
            musicData = list[musicNumber]
        } else {
            musicNumber += 1
            musicData = list[musicNumber]
        }
        NotificationChanges.musicData = musicData
        startPlay(musicData)
        return musicData
    }

    fun previousTrack(): MusicData? {
        val music: MusicData
        if (musicNumber == 0) {
            musicNumber = list.size - 1
            music = list[musicNumber]
        } else {
            musicNumber -= 1
            music = list[musicNumber]
        }
        NotificationChanges.musicData = music
        startPlay(music)
        return music
    }

    fun pause() {
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause()
        else mediaPlayer?.start()
    }

    private fun idetnifyNumSong(musicData: MusicData) {
        this.musicNumber = list.indexOf(musicData)
    }

    fun createPIntent() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val infoIntent = Intent(this, MainActivity::class.java).apply {
            action = Constants.ACTION.TRACK_INFO
            putExtra("Fragment", "track_info")
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pInfoIntent = PendingIntent.getActivity(this, 0, infoIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val nextIntent = Intent(MusicBinder().getService(), MusicService::class.java)
        nextIntent.action = Constants.ACTION.NEXT_TRACK
        val pNextIntent = PendingIntent.getService(MusicBinder().getService(), 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val prevIntent = Intent(MusicBinder().getService(), MusicService::class.java)
        prevIntent.action = Constants.ACTION.PREV_TRACK
        val pPrevIntent = PendingIntent.getService(this, 2, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val pauseIntent = Intent(MusicBinder().getService(), MusicService::class.java)
        pauseIntent.action = Constants.ACTION.PAUSE
        val pPauseIntent = PendingIntent.getService(this, 3, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder = Not.getNot(pInfoIntent, pPrevIntent, pPauseIntent, pNextIntent, this)
        notificationManager.notify(1, builder.build())
    }

}
