@file:Suppress("DEPRECATION")

package com.example.myapplication.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.constants.Constants
import com.example.myapplication.track_status.TrackStatus

object Notification {

    lateinit var builder: Notification.Builder

    fun getNotification(pInfoIntent: PendingIntent, pPrevIntent: PendingIntent, pPauseIntent: PendingIntent, pNextIntent: PendingIntent, context: Context): Notification.Builder {
        builder = Notification.Builder(context).apply {
            setVisibility(Notification.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.korz)
            setContentTitle(NotificationChanges.musicData.title)
            setContentText(NotificationChanges.musicData.author)
            setContentIntent(pInfoIntent)
            style = Notification.MediaStyle().apply {
                addAction(R.drawable.ic_skip_previous_24px, Constants.ACTION.PREV_TRACK, pPrevIntent)
                if (TrackStatus.status == Constants.ACTION.START) addAction(R.drawable.ic_pause_circle_filled_24px, Constants.ACTION.PAUSE, pPauseIntent)
                else addAction(R.drawable.ic_play_circle_filled_24px, Constants.ACTION.START, pPauseIntent)
                addAction(R.drawable.ic_skip_next_24px, Constants.ACTION.NEXT_TRACK, pNextIntent)
            }
        }
        return builder
    }
}
