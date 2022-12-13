package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.model.SortingItem
import dev.mohammadfayaz.sorting.model.SortingItemState
import java.util.Random

class RandomList {
  private val random: Random = Random()

  fun integers(size: Int = 20): MutableList<SortingItem> {
    val finalSize = if (size > 500) 500 else size
    val list: MutableList<SortingItem> = mutableListOf()
    for (i in 0 until finalSize) {
      list.add(SortingItem(value= random.nextInt(500), state = SortingItemState.IDLE))
    }
    return list
  }
}
