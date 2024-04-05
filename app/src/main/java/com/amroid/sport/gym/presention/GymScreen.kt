package com.amroid.sport.gym.presention

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.amroid.sport.gym.domain.Gym

@Composable
fun GymScreen(state: GymState, onFavoriteClicked: (id: Int) -> Unit, onItemClicked: (id: Int) -> Unit) {
  LazyColumn(content = {
    items(state.gym) {
      gymItem(it, onFavoriteClicked, onItemClicked)
    }
  })
  if (state.isLoading) CircularProgressIndicator()
  if (state.error != null) Text(text = state.error)

}

@Composable
fun gymItem(gym: Gym, onFavoirt: (id: Int) -> Unit, onItemClicked: (id: Int) -> Unit) {
  Card(
    modifier = Modifier
      .padding(8.dp)
      .clickable {
        onItemClicked(gym.id)
      },
    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
  ) {
    val icon = if (gym.isFav) {
      Icons.Outlined.Favorite
    } else {
      Icons.Outlined.FavoriteBorder
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
      DefaultICon(Icons.Filled.AccountCircle, Modifier.weight(.15f))
      gymInfo(gym.name, gym.desc, Modifier.weight(.70f), Alignment.CenterHorizontally)
      DefaultICon(
        icon,
        Modifier
          .weight(.15f)
          .clickable {
            onFavoirt(gym.id)
          },
      )
    }
  }
}

@Composable
fun gymInfo(
  name: String,
  desc: String,
  modifier: Modifier,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
  Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
    Text(text = name, style = MaterialTheme.typography.headlineMedium, color = Color.Black)
    Text(text = desc)
  }
}

@Composable
fun DefaultICon(image: ImageVector, modifier: Modifier) {
  Image(imageVector = image, contentDescription = "", modifier)
}

