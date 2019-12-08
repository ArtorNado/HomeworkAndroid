@file:Suppress("DEPRECATION")

package com.example.myapplication.music_list

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import com.example.myapplication.R

object Not {
    lateinit var builder: Notification.Builder

    fun getNot(pInfoIntent: PendingIntent, pPrevIntent: PendingIntent, pPauseIntent: PendingIntent, pNextIntent: PendingIntent, context: Context): Notification.Builder {
        builder = Notification.Builder(context).apply {
            setVisibility(Notification.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.korz)
            setContentTitle("Wonderful music")
            setContentText("My awesome band")
            setContentIntent(pInfoIntent)
            style = Notification.MediaStyle().apply {
                addAction(R.drawable.korz, "next", pNextIntent)
                addAction(R.drawable.korz, "previous", pPrevIntent)
                addAction(R.drawable.korz, "pause", pPauseIntent)
            }
        }
        return builder
    }
}
