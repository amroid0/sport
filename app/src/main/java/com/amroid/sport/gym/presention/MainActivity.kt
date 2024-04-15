package com.amroid.sport.gym.presention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amroid.sport.ui.theme.SportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SportTheme {
        GymAroundApp()
      }
    }
  }

  @Composable
  private fun GymAroundApp() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "gym") {
      composable("gym") {
        val viewModel = hiltViewModel<GymViewModel>()
        GymScreen(state = viewModel.state.value, onFavoriteClicked = {
          viewModel.favoriteGym(it)
        }, onItemClicked = {
          nav.navigate("gym_detail/$it")
        })
      }
      composable(
        "gym_detail/{gym_id}",
        arguments = listOf(
          navArgument("gym_id") {
            type = NavType.IntType
          },
        ),
      ) {
        GymDetailScreen()
      }

    }
  }
}

