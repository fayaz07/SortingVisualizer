package dev.mohammadfayaz.sorting.ui.sorting

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem

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
  val focusManager = LocalFocusManager.current

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
      onValueChange = { viewModel.updateItemsCount(it) },
      label = {
        Text(text = "Items count")
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
      )
    )
    Spacer(modifier = Modifier.padding(top = 8.dp))
    SpeedRadioButton(state, viewModel)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      ElevatedButton(
        onClick = {
          focusManager.clearFocus()
          viewModel.generateItems()
        },
        enabled = state.generateEnabled
      ) {
        Text(text = "Generate items")
      }
      ElevatedButton(
        modifier = Modifier.padding(start = 8.dp),
        onClick = {
          focusManager.clearFocus()
          viewModel.sort()
        },
        enabled = state.sortEnabled
      ) {
        Text(text = "Sort items")
      }
    }
    SortingVisual(state.max, viewModel.itemsState)
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
        if (item != SortingSpeed.NO_DELAY) {
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SortingVisual(max: Int, itemsState: SnapshotStateList<SortingItem>) {
  val maxWidth = LocalConfiguration.current.screenWidthDp - 32
  val lazyListState = rememberLazyListState()

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = 32.dp)
      .simpleVerticalScrollbar(lazyListState),
    state = lazyListState,
    horizontalAlignment = Alignment.Start,
  ) {
    items(itemsState.size) {
      Box(modifier = Modifier.animateItemPlacement()) {
        BarComposable(
          maxOffset = max, offset = itemsState[it].value, maxWidth = maxWidth,
          state = itemsState[it].state
        )
      }
    }
  }
}

fun Modifier.simpleVerticalScrollbar(
  state: LazyListState,
  width: Dp = 4.dp
): Modifier = composed {
  val targetAlpha = if (state.isScrollInProgress) 1f else 0f
  val duration = if (state.isScrollInProgress) 150 else 500

  val alpha by animateFloatAsState(
    targetValue = targetAlpha,
    animationSpec = tween(durationMillis = duration)
  )

  drawWithContent {
    drawContent()

    val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
    val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

    // Draw scrollbar if scrolling or if the animation is still running and lazy column has content
    if (needDrawScrollbar && firstVisibleElementIndex != null) {
      val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
      val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
      val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

      drawRect(
        color = Color(0xffbcbcbc),
        topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
        size = Size(width.toPx(), scrollbarHeight),
        alpha = alpha
      )
    }
  }
}
