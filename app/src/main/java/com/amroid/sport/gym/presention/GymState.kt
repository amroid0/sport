package com.amroid.sport.gym.presention

import com.amroid.sport.gym.domain.Gym

data class GymState(val gym: List<Gym>, val isLoading: Boolean = false, val error: String? = null)