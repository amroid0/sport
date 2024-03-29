package com.amroid.sport.ui

import com.amroid.sport.GymApp
import com.amroid.sport.GymService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymRepository {

  private var gymService: GymService =
    Retrofit.Builder().baseUrl("https://gymapi-e48a8-default-rtdb.firebaseio.com/")
      .addConverterFactory(GsonConverterFactory.create()).build().create(GymService::class.java)
  private val gymDao = GymDatabase.getDaoInstance(GymApp.getInstnace())

  suspend fun getAllGym() = withContext(Dispatchers.IO) {

    try {
      updateLocalDatabase()
    } catch (ex: Exception) {
    }
    gymDao.getAllGym()
  }

  suspend fun updateLocalDatabase() {
    val list = gymService.getGymList()
    val favoriteGyms = gymDao.getFavoriteGym()
    gymDao.addAllGym(list)
    gymDao.updateAllGym(favoriteGyms.map {
      UpdateGym(it.id, true)
    })
  }

  suspend fun favroitGym(id: Int, isfavrotie: Boolean): List<Gym> {
    return withContext(Dispatchers.IO) {
      gymDao.updateGym(UpdateGym(id, isfavrotie))
      gymDao.getAllGym()
    }
  }


}