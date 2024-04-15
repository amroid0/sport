package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository
import javax.inject.Inject

class GetInitialGymListUscCase @Inject constructor(
  val repository: GymRepository,
  val gymListUseCase: GetGymListUseCase
) {

  suspend operator fun invoke(): List<Gym> {
    repository.loadGymList()
    return gymListUseCase()
  }
}