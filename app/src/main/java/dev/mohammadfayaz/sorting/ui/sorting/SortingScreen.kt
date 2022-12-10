package dev.mohammadfayaz.sorting.ui.sorting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mohammadfayaz.sorting.algorithms.BubbleSort
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm

@Preview
@Composable
private fun SortingScreenPreview() {
  SortingScreen(BubbleSort())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingScreen(algorithm: SortingAlgorithm) {
  Scaffold(
    topBar = {
      TopAppBar(title = { Text(text = algorithm.name()) })
    }
  ) {
    Column(
      modifier = Modifier
        .padding(it)
        .padding(all = 8.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {

    }
  }
}
