package com.amroid.sport.ui

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymDao {
  @Query("select * from gym_table")
  suspend fun getAllGym(): List<Gym>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addAllGym(gym: List<Gym>)

  @Update(entity = Gym::class)
  suspend fun updateGym(updateGym: UpdateGym)

  @Update(entity = Gym::class)
  suspend fun updateAllGym(updateGym: List<UpdateGym>)

  @Query("select * from gym_table where is_fav = 1")
  suspend fun getFavoriteGym(): List<Gym>
}