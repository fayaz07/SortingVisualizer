package dev.mohammadfayaz.sorting.algorithms

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

abstract class SortingAlgorithmImpl {
  var speed: SortingSpeed = SortingSpeed.MEDIUM

  protected var list = mutableListOf<Int>()
  var listFlow = Channel<List<Int>>()

  abstract suspend fun genRandom(count: Int): List<Int>

  abstract suspend fun sort()
}
