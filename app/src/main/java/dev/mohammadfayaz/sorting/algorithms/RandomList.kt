package dev.mohammadfayaz.sorting.algorithms

import java.util.Random

class RandomList {
  private val random: Random = Random()

  fun integers(size: Int = 20): MutableList<Int> {
    val finalSize = if (size > 999) 999 else size
    val list: MutableList<Int> = mutableListOf()
    for (i in 0 until finalSize) {
      list.add(random.nextInt(999))
    }
    return list
  }
}
