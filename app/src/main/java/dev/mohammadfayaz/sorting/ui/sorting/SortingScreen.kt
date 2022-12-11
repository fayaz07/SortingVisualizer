package dev.mohammadfayaz.sorting.ui.sorting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed

@Preview
@Composable
private fun SortingScreenPreview() {
  SortingScreen(SortingAlgorithm.BUBBLE_SORT)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingScreen(algorithm: SortingAlgorithm) {
  val viewModel: SortingScreenViewModel = viewModel()
  val state = viewModel.state.collectAsState().value

  LaunchedEffect(Unit) {
    viewModel.setAlgorithm(algorithm)
  }

  Scaffold(
    topBar = {
      TopAppBar(title = { Text(text = algorithm.title) })
    }
  ) {
    Body(it, state, viewModel)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Body(
  paddingValues: PaddingValues,
  state: SortingScreenState,
  viewModel: SortingScreenViewModel
) {
  Column(
    modifier = Modifier
      .padding(paddingValues)
      .padding(all = 8.dp)
      .fillMaxSize()
  ) {
    OutlinedTextField(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
      value = state.count,
      onValueChange = { viewModel.updateItems(it) },
      label = {
        Text(text = "Items count")
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
      )
    )
    Spacer(modifier = Modifier.padding(top = 8.dp))
    SpeedRadioButton(state, viewModel)

    SortingVisual(state, viewModel)
  }
}

@Composable
private fun SpeedRadioButton(state: SortingScreenState, viewModel: SortingScreenViewModel) {
  val radioOptions = SortingSpeed.values()
  Column {
    Text(
      text = "Select sorting speed",
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.padding(start = 8.dp)
    )
    Row {
      radioOptions.forEach { item ->
        Row(
          modifier = Modifier
            .selectable(
              selected = (item == state.speed),
              onClick = {
                viewModel.selectSpeed(item)
              }
            )
            .padding(horizontal = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          RadioButton(
            selected = (item == state.speed),
            onClick = { viewModel.selectSpeed(item) }
          )
          Text(
            text = item.name,
            style = MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 4.dp),
          )
        }
      }
    }
  }
}

@Composable
private fun SortingVisual(
  state: SortingScreenState,
  viewModel: SortingScreenViewModel
) {
  LazyColumn {

  }
}
