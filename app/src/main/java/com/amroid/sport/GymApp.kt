package com.amroid.sport

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymApp : Application() {
  init {
    instance = this
  }

  companion object {
    private lateinit var instance: GymApp
    fun getInstnace() = instance.applicationContext
  }
}