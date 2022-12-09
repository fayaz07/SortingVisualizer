package dev.mohammadfayaz.sorting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.mohammadfayaz.sorting.ui.Routes
import dev.mohammadfayaz.sorting.ui.home.HomeScreen

@Composable
fun SortingVisualizerNavHost(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Routes.home) {
    composable(Routes.home) {
      HomeScreen()
    }
  }
}

