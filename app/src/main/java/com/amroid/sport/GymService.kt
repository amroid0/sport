package com.amroid.sport

import com.amroid.sport.ui.Gym
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GymService {
  @GET("gyms.json")
  suspend fun getGymList(): List<Gym>
  @GET("gyms.json?orderBy=\"id\"")
  suspend fun getGymDetail(@Query("equalTo")id:Int):Map<String,Gym>
}