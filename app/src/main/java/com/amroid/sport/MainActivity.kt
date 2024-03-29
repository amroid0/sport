package com.amroid.sport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amroid.sport.ui.GymDetailScreen
import com.amroid.sport.ui.GymScreen
import com.amroid.sport.ui.GymViewModel
import com.amroid.sport.ui.theme.SportTheme

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
        val viewModel = viewModel<GymViewModel>()
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

