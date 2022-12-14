package dev.mohammadfayaz.sorting.algorithms.sorting

import dev.mohammadfayaz.sorting.algorithms.RandomList
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem
import dev.mohammadfayaz.sorting.model.SortingItemState
import kotlinx.coroutines.channels.Channel

abstract class SortingAlgorithmImpl {
  var speed: SortingSpeed = SortingSpeed.MEDIUM

  protected var list = mutableListOf<SortingItem>()
  var listFlow = Channel<List<SortingItem>>()

  abstract suspend fun sort()

  fun genRandom(count: Int): List<SortingItem> {
    val randomIntegers = RandomList().integers(count)
    list = randomIntegers
    return list
  }

  protected fun selectAndUnselect(currentIndex: Int): MutableList<SortingItem> {
    for (i in 0 until list.size) {
      list[i].state = SortingItemState.IDLE
    }
    if (currentIndex != -1) {
      list[currentIndex].state = SortingItemState.SELECTED
    }
    return list
  }
}
