package com.amhsn.mycustomservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private val channelId = "com.example"
    private val channelName = "example"
    private val manager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    init {
        createChannel()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
    }

    fun getNotificationBuilder(
        title: String,
        msg: String,
        isOngoing: Boolean,
        isSilent: Boolean,
        intent: Intent? = null,
    ): NotificationCompat.Builder {

        var pendingIntent: PendingIntent? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
        }


        return NotificationCompat.Builder(context.applicationContext, channelId).apply {
            setContentText(msg)
            setContentTitle(title)
            setOngoing(isOngoing)
            setOnlyAlertOnce(false)
            setAutoCancel(true)
            if (isSilent) setNotificationSilent()
            priority = NotificationCompat.PRIORITY_HIGH
            pendingIntent?.let {
                setContentIntent(pendingIntent)
            }
            setSmallIcon(R.mipmap.ic_launcher)
        }

    }
}