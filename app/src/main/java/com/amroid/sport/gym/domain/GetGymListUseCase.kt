package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository

class GetGymListUseCase {
  private val repository = GymRepository()
  suspend operator fun invoke(): List<Gym> {
    return repository.getGymList().sortedBy { it.name }
  }
}