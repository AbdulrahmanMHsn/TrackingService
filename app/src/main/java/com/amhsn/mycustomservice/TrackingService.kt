package com.amhsn.mycustomservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class TrackingService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setupForegroundService("hH")

        uploadLocationToFirebase()

        return START_STICKY
    }


    private fun setupForegroundService(body: String) {
        val notification = NotificationHelper(this).getNotificationBuilder(
            title = "Nour",
            msg = body,
            isOngoing = false,
            isSilent = false,
            Intent(this,MainActivity::class.java)
        )

        startForeground(1, notification.build())
    }


    private fun uploadLocationToFirebase() {
        LocationHelper(this) {
            FirebaseUtils.getInstance().updateLocationList(1,it.latitude,it.longitude)
            Toast.makeText(this@TrackingService, "$it", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}