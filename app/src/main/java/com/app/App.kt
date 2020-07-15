package com.app

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class App : Application() {

    companion object {
        private var sharedPreferences: SharedPreferences? = null
        fun getSharedPreferences(): SharedPreferences {
            return sharedPreferences!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        App.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }
}