package dev.mohammadfayaz.sorting.ui.sorting

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mohammadfayaz.sorting.algorithms.FindMax
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SortingScreenViewModel : ViewModel() {
  private val _state = MutableStateFlow(
    SortingScreenState(
      count = "",
      speed = SortingSpeed.MEDIUM,
      sortingAlgorithm = SortingAlgorithm.BUBBLE_SORT,
      sortEnabled = false,
      generateEnabled = false
    )
  )
  val state: StateFlow<SortingScreenState> = _state

  private val _itemsState = mutableStateListOf<SortingItem>()
  val itemsState = _itemsState

  private var sortingJob: Job? = null

  fun setAlgorithm(value: SortingAlgorithm) {
    viewModelScope.launch {
      _state.emit(state.value.copy(sortingAlgorithm = value))

      value.impl.listFlow.receiveAsFlow().collect {
        _itemsState.clear()
        _itemsState.addAll(it)
      }
    }
  }

  fun updateItemsCount(value: String) {
    viewModelScope.launch {
      val filtered = value.filter { c -> c.isDigit() }
      _state.emit(
        state.value.copy(
          count = filtered,
          generateEnabled = filtered.isNotEmpty() && Integer.parseInt(filtered) > 2
        )
      )
    }
  }

  fun generateItems() {
    viewModelScope.launch {
      if (sortingJob?.isActive == true) {
        sortingJob?.cancel()
      }
      var count = _state.value.count
      if (count == "") {
        count = 0.toString()
      }
      val algorithm = state.value.sortingAlgorithm
      val list = algorithm.impl.genRandom(Integer.parseInt(count))

      _itemsState.clear()
      _itemsState.addAll(list)
      val maxValue = FindMax().inIntegers(list)
      _state.emit(
        state.value.copy(
          max = maxValue, sortingAlgorithm = algorithm, sortEnabled = true
        )
      )
    }
  }

  fun sort() {
    viewModelScope.launch {
      _state.emit(state.value.copy(sortEnabled = false, generateEnabled = false))
      state.value.sortingAlgorithm.impl.sort()
      _state.emit(state.value.copy(sortEnabled = true, generateEnabled = true))
    }
  }

  fun selectSpeed(value: SortingSpeed) {
    viewModelScope.launch {
      _state.emit(state.value.copy(speed = value))
      state.value.sortingAlgorithm.impl.speed = value
    }
  }
}
