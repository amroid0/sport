package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository

class GetInitialGymListUscCase {
  private val repository = GymRepository()
  private val gymListUseCase = GetGymListUseCase()
  suspend operator fun invoke():List<Gym> {
    repository.loadGymList()
    return gymListUseCase()
  }
}