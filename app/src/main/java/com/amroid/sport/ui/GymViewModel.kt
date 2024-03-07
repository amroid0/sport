package com.amroid.sport.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amroid.sport.GymService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {
  var state by mutableStateOf(listOf<Gym>())
  private var gymService: GymService
  private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    throwable.printStackTrace()
  }

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://gymapi-e48a8-default-rtdb.firebaseio.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    gymService = retrofit.create(GymService::class.java)
    getGymList()
  }


  fun getGymList() {
    viewModelScope.launch(exceptionHandler) {
      val list = withContext(Dispatchers.IO) { gymService.getGymList() }
      state = list.restoreFavIds()
    }
  }

  fun favoriteGym(id: Int) {
    val list = state.toMutableList()
    val gymIndex = list.indexOfFirst { id == it.id }
    list[gymIndex] = list[gymIndex].copy(isFav = !list[gymIndex].isFav)
    storeFavoriteIDs(list[gymIndex])
    state = list
  }

  private fun List<Gym>.restoreFavIds(): List<Gym> {

    savedStateHandle.get<List<Int>?>(KEY_FAV)?.let { gymIds ->
      gymIds.forEach { id ->
        this.find { it.id == id }?.isFav = true
      }
    }
    return this
  }

  private fun storeFavoriteIDs(gym: Gym) {
    val stateList = savedStateHandle.get<List<Int>?>(KEY_FAV).orEmpty().toMutableList()
    if (gym.isFav) {
      stateList.add(gym.id)
    } else {
      stateList.remove(gym.id)
    }
    savedStateHandle[KEY_FAV] = stateList
  }

  companion object {
    const val KEY_FAV = "KEY_FAV"
  }
}