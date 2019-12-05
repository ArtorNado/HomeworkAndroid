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
import android.util.Log
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R


@SuppressLint("Registered")
class MusicService : Service(), Change {

    private var mediaPlayer = MediaPlayer()
    private var list = MusicRepository.getMusicList()
    private var musicNumber = 0
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private lateinit var mChange: Change

    inner class MusicBinder : Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action.equals(Constants.ACTION.NEXT_TRACK)) {
            stopPlay()
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
        sendNotif()
        return MusicBinder()
    }

    fun startPlay(musicData: MusicData) {
        idetnifyNumSong(musicData)
        stopPlay()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer.create(applicationContext, musicData.music)
        mediaPlayer.start()
        mChange = MainActivity()
        mChange.change(musicData)
    }

    fun stopPlay() {
        mediaPlayer.stop()
    }

    fun nextTrack(): MusicData? {
        val music: MusicData
        if (list.size == musicNumber + 1) {
            music = list.get(0)
            musicNumber = 0
        } else {
            music = list.get(musicNumber + 1)
            musicNumber++
        }
        startPlay(music)
        return music
    }

    fun previousTrack(): MusicData? {
        val music: MusicData
        if (musicNumber == 0) {
            music = list.get(1)
        } else {
            music = list.get(musicNumber - 1)
        }
        return music
    }

    fun pause() {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()
        else mediaPlayer.start()
    }

    private fun idetnifyNumSong(musicData: MusicData) {
        this.musicNumber = list.indexOf(musicData)
    }

    private fun sendNotif() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val nextIntent: Intent = Intent(this, MusicService::class.java)
        nextIntent.action = Constants.ACTION.NEXT_TRACK
        val pNextIntent = PendingIntent.getService(this, 0, nextIntent, 0)
        Log.d("DOING", "DOIIIIIIING")
        builder = Notification.Builder(this).apply {
            setVisibility(Notification.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.korz)
            setContentTitle("Wonderful music")
            setContentText("My awesome band")
            addAction(R.drawable.korz, "next", pNextIntent)
            /*style = Notification.MediaStyle().apply {

            }*/
        }
        notificationManager.notify(1, builder.build())
    }

    override fun change(musicData: MusicData) {
    }

}
