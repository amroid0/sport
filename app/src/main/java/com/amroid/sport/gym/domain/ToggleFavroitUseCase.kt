package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository
import javax.inject.Inject

class ToggleFavroitUseCase @Inject constructor(
  val repository: GymRepository,
  val gymListUseCase: GetGymListUseCase
) {

  suspend operator fun invoke(id: Int, isFav: Boolean): List<Gym> {
    repository.toggleFavoriteGym(id, isFav.not())
    return gymListUseCase()
  }
}