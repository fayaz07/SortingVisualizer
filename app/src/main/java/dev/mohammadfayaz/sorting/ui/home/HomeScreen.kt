package dev.mohammadfayaz.sorting.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
  Scaffold(
    topBar = {
      TopAppBar(title = { Text(text = "Sorting Visualizer") })
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(paddingValues)
        .padding(top = 32.dp, start = 16.dp, end = 16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Choose the sorting algorithm",
        style = MaterialTheme.typography.bodyLarge
      )
      Spacer(modifier = Modifier.padding(top = 8.dp))

      for (algorithm in SortingAlgorithm.values()) {
        ElevatedButton(
          onClick = { },
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        ) {
          Text(text = algorithm.title)
        }
      }
    }
  }
}

@Preview
@Composable
private fun HomeScreenPreview() {
  HomeScreen()
}
