package com.example.myapplication.music_list

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import com.example.myapplication.MainActivity
import com.example.myapplication.constants.Constants
import com.example.myapplication.notification.NotificationChanges
import com.example.myapplication.repository.MusicRepository
import com.example.myapplication.track_status.TrackStatus


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
            Constants.ACTION.NEXT_TRACK -> {
                this.MusicBinder().getService().nextTrack()
                createNotification()
            }
            Constants.ACTION.PREV_TRACK -> {
                this.MusicBinder().getService().previousTrack()
                createNotification()
            }
            Constants.ACTION.PAUSE -> {
                this.MusicBinder().getService().pause()
                createNotification()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        createNotificationChannel()
        createNotification()
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
        return MusicBinder()
    }

    fun startPlay(musicData: MusicData) {
        setNumbTrack(musicData)
        stopPlay()
        NotificationChanges.musicData = musicData
        mediaPlayer = MediaPlayer.create(applicationContext, musicData.music)
        mediaPlayer?.start()
        TrackStatus.status = Constants.ACTION.START
        createNotification()
    }

    fun stopPlay() {
        mediaPlayer?.stop()
        TrackStatus.status = Constants.ACTION.STOP
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
        if (TrackStatus.status == Constants.ACTION.START) startPlay(musicData)
        else {
            stopPlay()
            TrackStatus.status = Constants.ACTION.STOP
            NotificationChanges.musicData = currentTrack()

        }
        return musicData
    }

    fun previousTrack(): MusicData? {
        val musicData: MusicData
        if (musicNumber == 0) {
            musicNumber = list.size - 1
            musicData = list[musicNumber]
        } else {
            musicNumber -= 1
            musicData = list[musicNumber]
        }
        NotificationChanges.musicData = musicData
        if (TrackStatus.status == Constants.ACTION.START) startPlay(musicData)
        else {
            stopPlay()
            TrackStatus.status = Constants.ACTION.STOP
            NotificationChanges.musicData = currentTrack()
        }
        return musicData
    }

    fun pause() {
        when (TrackStatus.status) {
            Constants.ACTION.START -> {
                mediaPlayer?.pause()
                TrackStatus.status = Constants.ACTION.PAUSE
            }
            Constants.ACTION.PAUSE -> {
                mediaPlayer?.start()
                TrackStatus.status = Constants.ACTION.START
            }
            Constants.ACTION.STOP -> {
                startPlay(currentTrack())
            }
        }
    }

    private fun setNumbTrack(musicData: MusicData) {
        this.musicNumber = list.indexOf(musicData)
    }

    private fun currentTrack(): MusicData = list[musicNumber]

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val name = "Music channel"
            val description = "My music player"
            val mChannel = NotificationChannel(Constants.NOTIFICATION.ARG_NOTIFICATION_ID, name, importance)
            mChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun createNotification() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val infoIntent = Intent(this, MainActivity::class.java).apply {
            action = Constants.FILTER.TRACK_INFO
            putExtra("Fragment", Constants.FILTER.TRACK_INFO)
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
        builder = com.example.myapplication.notification.Notification.getNotification(pInfoIntent, pPrevIntent, pPauseIntent, pNextIntent, this)
        notificationManager.notify(1, builder.build())
    }

}
