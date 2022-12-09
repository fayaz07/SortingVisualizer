package dev.mohammadfayaz.sorting.algorithms

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class SortingAlgorithm {
  var list = mutableListOf<Int>()
  protected var _listFlow = MutableSharedFlow<List<Int>>()
  val listFlow: SharedFlow<List<Int>> = _listFlow

  abstract suspend fun sort()
}
