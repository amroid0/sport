package com.amroid.sport.gym.domain

import com.amroid.sport.gym.data.GymRepository
import javax.inject.Inject

class GetGymListUseCase @Inject constructor(val repository : GymRepository){
  suspend operator fun invoke(): List<Gym> {
    return repository.getGymList().sortedBy { it.name }
  }
}