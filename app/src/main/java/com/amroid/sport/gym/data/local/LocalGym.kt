package com.amroid.sport.gym.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "gym_table")
data class LocalGym(
  @PrimaryKey
  @ColumnInfo(name = "gym_id")
  val id: Int,
  @ColumnInfo(name = "gym_name")
  val name: String,
  @ColumnInfo(name = "gym_desc")
  val desc: String,
  val isOpen: Boolean,
  @ColumnInfo(name="is_fav")
  val isFav: Boolean = false,
)

