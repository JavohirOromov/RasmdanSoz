package com.example.rasmdansoz.app;

import android.app.Application
import com.example.rasmdansoz.storage.LocalStorage

/**
 * Creator: Javohir Oromov
 * Project: RasmdanSo'z
 * Date: 26/03/25
 * Javohir's MacBook Air
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}