package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository

class ToggleFavroitUseCase {
  private val repository = GymRepository()
  private val gymListUseCase = GetGymListUseCase()
  suspend operator fun invoke(id: Int, isFav: Boolean): List<Gym> {
    repository.toggleFavoriteGym(id, isFav.not())
    return gymListUseCase()
  }
}