package com.amroid.sport.gym.domain

data class Gym(
  val id: Int,

  val name: String,

  val desc: String,

  val isOpen: Boolean,

  val isFav: Boolean = false,
)

