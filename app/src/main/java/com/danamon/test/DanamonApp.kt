package com.danamon.test

import androidx.appcompat.app.AppCompatDelegate
import com.danamon.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DanamonApp: BaseApplication() {
    override fun getBaseUrl(): String = BuildConfig.BASE_URL

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}