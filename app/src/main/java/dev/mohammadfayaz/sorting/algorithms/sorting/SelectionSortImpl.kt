package dev.mohammadfayaz.sorting.algorithms.sorting

import dev.mohammadfayaz.sorting.algorithms.delayAfterSort
import dev.mohammadfayaz.sorting.algorithms.delayBeforeSort
import dev.mohammadfayaz.sorting.model.SortingItem
import dev.mohammadfayaz.sorting.model.SortingItemState
import kotlinx.coroutines.delay

class SelectionSortImpl : SortingAlgorithmImpl() {
  override suspend fun sort() {
    for (i in 0 until list.size) {
      listFlow.send(selectAndUnselect(i))
      delay(speed.delayBeforeSort())

      // find minimum element and place it at position i
      val minIndex = findMin(list, i)

      if (minIndex > i) {
        val x = list[minIndex]
        list[minIndex] = list[i]
        list[i] = x

        list[i].state = SortingItemState.SWAPPING
        list[minIndex].state = SortingItemState.SWAPPING
      }

      listFlow.send(list)
      delay(speed.delayAfterSort())
    }
    listFlow.send(selectAndUnselect(-1))
  }

  private suspend fun findMin(list: MutableList<SortingItem>, start: Int): Int {
    var min = Integer.MAX_VALUE
    var minIndex = -1

    for (i in start until list.size) {
      selectAndUnselect(start)
      list[i].state = SortingItemState.SELECTED
      listFlow.send(list)
      delay(speed.delayBeforeSort())

      if (min > list[i].value) {
        min = list[i].value
        minIndex = i
      }
    }
    listFlow.send(selectAndUnselect(minIndex))
    delay(speed.delayBeforeSort())

    println("min $min $minIndex")
    return minIndex
  }
}
