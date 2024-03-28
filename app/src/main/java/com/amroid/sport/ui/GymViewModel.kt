package com.amroid.sport.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amroid.sport.GymApp
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

class GymViewModel : ViewModel() {
  var state by mutableStateOf(listOf<Gym>())
  private var gymService: GymService
  private val gymDao = GymDatabase.getDaoInstance(GymApp.getInstnace())
  private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    throwable.printStackTrace()
  }

  init {
    val retrofit = Retrofit.Builder().baseUrl("https://gymapi-e48a8-default-rtdb.firebaseio.com/")
      .addConverterFactory(GsonConverterFactory.create()).build()
    gymService = retrofit.create(GymService::class.java)
    getGymList()
  }


  fun getGymList() {
    viewModelScope.launch {
      state = getAllGyms()
    }
  }

  private suspend fun getAllGyms() = withContext(Dispatchers.IO) {

    try {
      updateLocalDatabase()
    } catch (ex: Exception) {
    }
    gymDao.getAllGym()
  }

  private suspend fun updateLocalDatabase() {
    val list = gymService.getGymList()
    val favoriteGyms = gymDao.getFavoriteGym()
    gymDao.addAllGym(list)
    gymDao.updateAllGym(favoriteGyms.map {
      UpdateGym(it.id, true)
    })
  }


  fun favoriteGym(id: Int) {
    val list = state.toMutableList()
    val gymIndex = list.indexOfFirst { id == it.id }
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        gymDao.updateGym(UpdateGym(id, !list[gymIndex].isFav))
        state = gymDao.getAllGym()
      }
    }

  }

}