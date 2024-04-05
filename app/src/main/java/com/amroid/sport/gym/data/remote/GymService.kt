package com.amroid.sport.gym.data.remote

import com.amroid.sport.gym.domain.Gym
import retrofit2.http.GET
import retrofit2.http.Query

interface GymService {
  @GET("gyms.json")
  suspend fun getGymList(): List<RemoteGym>
  @GET("gyms.json?orderBy=\"id\"")
  suspend fun getGymDetail(@Query("equalTo")id:Int):Map<String, Gym>
}