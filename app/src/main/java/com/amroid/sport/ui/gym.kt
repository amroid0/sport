package com.amroid.sport.ui

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gym_table")
data class Gym(
  @PrimaryKey
  @ColumnInfo(name = "gym_id")
  val id: Int,
  @SerializedName("gym_name")
  @ColumnInfo(name = "gym_name")
  val name: String,
  @SerializedName("gym_location")
  @ColumnInfo(name = "gym_desc")
  val desc: String,
  @SerializedName("is_open")
  val isOpen: Boolean,
  @ColumnInfo("is_fav")
  val isFav: Boolean = false,
)

