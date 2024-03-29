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
  var state by mutableStateOf(GymState(arrayListOf(), isLoading = true))
   private val gymRepo = GymRepository()
  private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    throwable.printStackTrace()
    state = state.copy(isLoading = false, error = "${throwable.message}")
  }

  init {
    getGymList()
  }


  fun getGymList() {
    viewModelScope.launch {
   val list = getAllGyms()
      state = state.copy(gym = list,isLoading = false)
    }
  }

  private suspend fun getAllGyms() = gymRepo.getAllGym()




  fun favoriteGym(id: Int) {
    val list = state.gym
    val gymIndex = list.indexOfFirst { id == it.id }
    viewModelScope.launch {
     val updateList = gymRepo.favroitGym(id, !list[gymIndex].isFav)
      state =  state.copy(gym = updateList,isLoading = false)
    }

  }

}