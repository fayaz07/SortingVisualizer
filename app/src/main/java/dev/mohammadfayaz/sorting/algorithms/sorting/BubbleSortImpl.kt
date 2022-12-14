package dev.mohammadfayaz.sorting.algorithms.sorting

import dev.mohammadfayaz.sorting.algorithms.delayAfterSort
import dev.mohammadfayaz.sorting.algorithms.delayBeforeSort
import dev.mohammadfayaz.sorting.model.SortingItemState
import kotlinx.coroutines.delay

class BubbleSortImpl : SortingAlgorithmImpl() {

  override suspend fun sort() {
    for (i in 0 until list.size) {
      listFlow.send(selectAndUnselect(i))
      delay(speed.delayBeforeSort())

      for (j in (i + 1) until list.size) {
        list[j].state = SortingItemState.SELECTED
        listFlow.send(list)
        delay(speed.delayBeforeSort())

        if (list[i].value > list[j].value) {
          // swap both of them
          val x = list[j]
          list[j] = list[i]
          list[i] = x

          list[i].state = SortingItemState.SWAPPING
          list[j].state = SortingItemState.SWAPPING
        }

        listFlow.send(list)
        delay(speed.delayAfterSort())

        listFlow.send(selectAndUnselect(i))
        delay(speed.delayBeforeSort())
      }
      listFlow.send(selectAndUnselect(-1))
      delay(speed.delayBeforeSort())
    }
  }
}
