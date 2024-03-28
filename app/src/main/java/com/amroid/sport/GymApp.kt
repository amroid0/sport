package com.amroid.sport

import android.app.Application

class GymApp : Application() {
  init {
    instance = this
  }

  companion object {
    private lateinit var instance: GymApp
    fun getInstnace() = instance.applicationContext
  }
}