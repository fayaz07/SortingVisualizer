package dev.mohammadfayaz.sorting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dev.mohammadfayaz.sorting.navigation.SortingVisualizerNavHost
import dev.mohammadfayaz.sorting.ui.theme.SortingVisualizerTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SortingVisualizerTheme {
        val navHostController = rememberNavController()
        SortingVisualizerNavHost(navController = navHostController)
      }
    }
  }
}
