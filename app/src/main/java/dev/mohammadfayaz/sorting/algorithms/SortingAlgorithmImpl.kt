package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.channels.Channel

abstract class SortingAlgorithmImpl {
  var speed: SortingSpeed = SortingSpeed.MEDIUM

  protected var list = mutableListOf<SortingItem>()
  var listFlow = Channel<List<SortingItem>>()

  abstract suspend fun genRandom(count: Int): List<SortingItem>

  abstract suspend fun sort()
}
