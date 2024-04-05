package com.amroid.sport.gym.presention

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GymDetailScreen() {
  val viewModel = viewModel<GymDetailViewmodel>()
  val item = viewModel.state
  item?.let {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(16.dp)){
      DefaultICon(
        image = Icons.Filled.LocationOn, modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
      )
      gymInfo(
        name = item.name,
        desc = item.desc,
        modifier = Modifier.padding(bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      )
      Text(
        text = if (item.isOpen) "Gym is Open" else "Gym is Closed",
        color = if (item.isOpen) Color.Green else Color.Red
      )
    }
  }
}