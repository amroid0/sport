package com.amroid.sport.gym.data

import com.amroid.sport.GymApp
import com.amroid.sport.gym.data.local.GymDao
import com.amroid.sport.gym.data.remote.GymService
import com.amroid.sport.gym.domain.Gym
import com.amroid.sport.gym.data.local.GymDatabase
import com.amroid.sport.gym.data.local.LocalGym
import com.amroid.sport.gym.data.local.LocalUpdateGym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GymRepository @Inject constructor(val gymService: GymService,val gymDao:GymDao){


  suspend fun loadGymList() = withContext(Dispatchers.IO) {
    try {
      loadGyms()
    } catch (ex: Exception) {
    }
  }

  private suspend fun loadGyms() {
    val list = gymService.getGymList()
    val favoriteGyms = gymDao.getFavoriteGym()
    gymDao.addAllGym(list.map { LocalGym(it.id, it.name, it.desc, it.isOpen) })
    gymDao.updateAllGym(favoriteGyms.map {
      LocalUpdateGym(it.id, true)
    })
  }

  suspend fun toggleFavoriteGym(id: Int, isFavorite: Boolean){
    return withContext(Dispatchers.IO) {
      gymDao.updateGym(LocalUpdateGym(id, isFavorite))
    }
  }

  suspend fun getGymList() =
    gymDao.getAllGym().map { Gym(it.id, it.name, it.desc, it.isOpen, it.isFav) }


}