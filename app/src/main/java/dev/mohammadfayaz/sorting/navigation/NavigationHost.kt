package dev.mohammadfayaz.sorting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.ui.home.HomeScreen
import dev.mohammadfayaz.sorting.ui.sorting.SortingScreen

@Composable
fun SortingVisualizerNavHost(navController: NavHostController) {
  NavHost(navController = navController, startDestination = Routes.home) {
    composable(Routes.home) {
      HomeScreen { sortingAlgorithm ->
        navController.navigate(sortingAlgorithm.route)
      }
    }
    for (algorithm in SortingAlgorithm.values()) {
      composable(algorithm.route) {
        SortingScreen(algorithm = algorithm)
      }
    }
  }
}
