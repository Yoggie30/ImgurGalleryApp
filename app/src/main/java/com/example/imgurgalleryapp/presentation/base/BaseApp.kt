package com.example.imgurgalleryapp.presentation.base

import android.app.Application
import com.example.imgurgalleryapp.BuildConfig

import timber.log.Timber

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}