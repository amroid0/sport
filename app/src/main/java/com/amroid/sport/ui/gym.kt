package com.amroid.sport.ui

import com.google.gson.annotations.SerializedName

data class Gym(
  val id: Int,
  @SerializedName("gym_name") val name: String,
  @SerializedName("gym_location") val desc: String,
  @SerializedName("is_open") val isOpen: Boolean,
  var isFav: Boolean = false,
)

