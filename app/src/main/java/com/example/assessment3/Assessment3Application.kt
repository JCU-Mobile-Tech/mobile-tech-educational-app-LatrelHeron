package com.example.assessment3

import android.app.Application
import com.example.assessment3.di.AppContainer

class Assessment3Application : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(this)
    }
}