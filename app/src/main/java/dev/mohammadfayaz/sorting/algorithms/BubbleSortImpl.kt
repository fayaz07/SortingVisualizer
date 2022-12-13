package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.model.SortingItem
import dev.mohammadfayaz.sorting.model.SortingItemState
import kotlinx.coroutines.delay

class BubbleSortImpl : SortingAlgorithmImpl() {
  override suspend fun genRandom(count: Int): MutableList<SortingItem> {
    val randomIntegers = RandomList().integers(count)
    list = randomIntegers
    return list
  }

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

  private fun selectAndUnselect(currentIndex: Int): MutableList<SortingItem> {
    val unselectedList = list
    for (i in 0 until unselectedList.size) {
      unselectedList[i].state = SortingItemState.IDLE
    }
    if (currentIndex != -1) {
      unselectedList[currentIndex].state = SortingItemState.SELECTED
    }
    return unselectedList
  }
}
