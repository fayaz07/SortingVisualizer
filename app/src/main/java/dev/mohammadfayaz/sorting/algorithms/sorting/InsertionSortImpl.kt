package dev.mohammadfayaz.sorting.algorithms.sorting

import dev.mohammadfayaz.sorting.algorithms.delayAfterSort
import dev.mohammadfayaz.sorting.algorithms.delayBeforeSort
import dev.mohammadfayaz.sorting.model.SortingItemState
import kotlinx.coroutines.delay

class InsertionSortImpl : SortingAlgorithmImpl() {
  override suspend fun sort() {
    for (step in 1 until list.size) {
      val key = list[step]

      selectAndUnselect(step)
      listFlow.send(list)
      delay(speed.delayBeforeSort())

      var j = step - 1

      while (j >= 0 && key.value < list[j].value) {
        list[j + 1] = list[j]

        selectAndUnselect(step)
        list[j].state = SortingItemState.SWAPPING
        list[j + 1].state = SortingItemState.SWAPPING
        listFlow.send(list)
        delay(speed.delayAfterSort())

        --j
      }

      list[j + 1] = key
    }

    listFlow.send(list)
    selectAndUnselect(-1)
    listFlow.send(list)
    delay(speed.delayAfterSort())
  }
}
