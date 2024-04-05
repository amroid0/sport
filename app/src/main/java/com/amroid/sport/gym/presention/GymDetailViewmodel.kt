package com.amroid.sport.gym.presention

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amroid.sport.gym.data.remote.GymService
import com.amroid.sport.gym.domain.Gym
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymDetailViewmodel(val savedStateHandle: SavedStateHandle) : ViewModel() {
  private var gymService: GymService
  var state by mutableStateOf<Gym?>(null)

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://gymapi-e48a8-default-rtdb.firebaseio.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    gymService = retrofit.create(GymService::class.java)
    getGym(savedStateHandle.get<Int>("gym_id") ?: 0)
  }

  private fun getGym(id: Int) {
    viewModelScope.launch {
      state = gymService.getGymDetail(id).values.first()
    }
  }
}