package com.amhsn.mycustomservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.amhsn.mycustomservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val workManager = WorkManager.getInstance(this)


        binding.btnStart.setOnClickListener {
//            val workRequest = OneTimeWorkRequest.from(TrackingWorker::class.java)
//            workManager.enqueue(workRequest)
            startService()

        }

        binding.btnStop.setOnClickListener {

        }

    }

    private fun startService() {
        val gattServiceIntent = Intent(this, TrackingService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(gattServiceIntent)
        } else {
            startService(gattServiceIntent)
        }
    }
}