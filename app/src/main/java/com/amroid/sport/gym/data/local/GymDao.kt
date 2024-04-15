package com.amroid.sport.gym.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amroid.sport.gym.domain.Gym

@Dao
interface GymDao {
  @Query("select * from gym_table")
  suspend fun getAllGym(): List<LocalGym>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addAllGym(gym: List<LocalGym>)

  @Update(entity = LocalGym::class)
  suspend fun updateGym(localUpdateGym: LocalUpdateGym)

  @Update(entity = LocalGym::class)
  suspend fun updateAllGym(localUpdateGym: List<LocalUpdateGym>)

  @Query("select * from gym_table where is_fav = 1")
  suspend fun getFavoriteGym(): List<LocalGym>
}