package com.zat.assessmentapp.utils

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PixabayApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}