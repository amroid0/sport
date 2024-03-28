package com.amroid.sport.ui

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity()
data class UpdateGym(
  @ColumnInfo(name = "gym_id")
  val id: Int,
  @ColumnInfo("is_fav")
  val isFav: Boolean = false,
)

