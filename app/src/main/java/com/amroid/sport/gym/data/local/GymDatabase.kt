package com.amroid.sport.gym.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [LocalGym::class], version = 1, exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {
  abstract val dao: GymDao
}