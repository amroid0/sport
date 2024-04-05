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

  companion object {
    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(context, GymDatabase::class.java, "gym_db")
        .fallbackToDestructiveMigration().build()


    @Volatile
    private var daoInstnace: GymDao? = null
    public fun getDaoInstance(context: Context): GymDao {
      synchronized(this) {
        if (daoInstnace == null) {
          daoInstnace = buildDatabase(context).dao
        }
      }
      return daoInstnace as GymDao
    }
  }
}