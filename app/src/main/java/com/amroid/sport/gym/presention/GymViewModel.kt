package com.amroid.sport.gym.presention

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amroid.sport.gym.domain.GetInitialGymListUscCase
import com.amroid.sport.gym.domain.ToggleFavroitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GymViewModel @Inject constructor(
  val getInitialGymListUscCase: GetInitialGymListUscCase,
  val toggleFavoriteUscCase: ToggleFavroitUseCase
) : ViewModel() {
  var _state by mutableStateOf(GymState(arrayListOf(), isLoading = true))
  val state: State<GymState>
    get() = derivedStateOf { _state }
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
      _state = _state.copy(gym = list, isLoading = false)
    }
  }

  private suspend fun getAllGyms() = getInitialGymListUscCase()


  fun favoriteGym(id: Int) {
    val list = state.value.gym
    val gymIndex = list.indexOfFirst { id == it.id }
    viewModelScope.launch {
      val updateList = toggleFavoriteUscCase(id, list[gymIndex].isFav)
      _state = _state.copy(gym = updateList, isLoading = false)
    }

  }

}