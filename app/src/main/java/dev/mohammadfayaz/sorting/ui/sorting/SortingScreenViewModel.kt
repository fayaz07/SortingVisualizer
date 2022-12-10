package dev.mohammadfayaz.sorting.ui.sorting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mohammadfayaz.sorting.algorithms.RandomList
import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SortingScreenViewModel : ViewModel() {
  private lateinit var algorithm: SortingAlgorithm
  private val _state = MutableStateFlow(
    SortingScreenState(
      count = "",
      speed = SortingSpeed.MEDIUM
    )
  )
  val state: StateFlow<SortingScreenState> = _state

  lateinit var items: SharedFlow<List<Int>>

  private val random: RandomList = RandomList()

  fun setAlgorithm(value: SortingAlgorithm) {
    algorithm = value
    items = value.impl.listFlow
  }

  fun updateItems(value: String) {
    viewModelScope.launch {
      _state.emit(state.value.copy(count = value))
    }
  }

  fun sort() {
    viewModelScope.launch {
      if (::algorithm.isInitialized.not()) {
        return@launch
      }
      algorithm.impl.list = random.integers(Integer.parseInt(state.value.count))
      algorithm.impl.sort()
    }
  }

  fun selectSpeed(value: SortingSpeed) {
    viewModelScope.launch {
      _state.emit(state.value.copy(speed = value))
    }
  }
}
