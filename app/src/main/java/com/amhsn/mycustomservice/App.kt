package com.amhsn.mycustomservice

import android.app.Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseUtils.getInstance().init()
    }
}