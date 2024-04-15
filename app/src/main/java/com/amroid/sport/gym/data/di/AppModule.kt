package com.amroid.sport.gym.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amroid.sport.GymApp
import com.amroid.sport.gym.data.local.GymDao
import com.amroid.sport.gym.data.local.GymDatabase
import com.amroid.sport.gym.data.remote.GymService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  fun provideGymServices(): GymService {
    return Retrofit.Builder().baseUrl("https://gymapi-e48a8-default-rtdb.firebaseio.com/")
      .addConverterFactory(GsonConverterFactory.create()).build().create(GymService::class.java)

  }

  @Provides
  @Singleton
  fun provideGymDao(@ApplicationContext appContext: Context): GymDao {
    val database = Room.databaseBuilder(appContext, GymDatabase::class.java, "gym_db")
      .fallbackToDestructiveMigration().build()
    return database.dao
  }
}