package com.amroid.sport.gym.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity()
data class LocalUpdateGym(
  @ColumnInfo(name = "gym_id")
  val id: Int,
  @ColumnInfo("is_fav")
  val isFav: Boolean = false,
)

