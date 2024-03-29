package com.amroid.sport.ui

data class GymState(val gym: List<Gym>, val isLoading: Boolean = false, val error: String? = null)