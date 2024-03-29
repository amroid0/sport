package com.amroid.sport.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
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
  var _state by mutableStateOf(GymState(arrayListOf(), isLoading = true))
  val state:State<GymState>
    get() = derivedStateOf { _state }
   private val gymRepo = GymRepository()
  private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    throwable.printStackTrace()
    _state = _state.copy(isLoading = false, error = "${throwable.message}")
  }

  init {
    getGymList()
  }


  private fun getGymList() {
    viewModelScope.launch {
   val list = getAllGyms()
      _state = _state.copy(gym = list,isLoading = false)
    }
  }

  private suspend fun getAllGyms() = gymRepo.getAllGym()




  fun favoriteGym(id: Int) {
    val list = state.value.gym
    val gymIndex = list.indexOfFirst { id == it.id }
    viewModelScope.launch {
     val updateList = gymRepo.favroitGym(id, !list[gymIndex].isFav)
      _state = _state.copy(gym = updateList,isLoading = false)
    }

  }

}