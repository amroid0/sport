package com.amroid.sport.gym.data.remote
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class RemoteGym(
  @PrimaryKey
  val id: Int,
  @SerializedName("gym_name")
  val name: String,
  @SerializedName("gym_location")
  val desc: String,
  @SerializedName("is_open")
  val isOpen: Boolean,
)

