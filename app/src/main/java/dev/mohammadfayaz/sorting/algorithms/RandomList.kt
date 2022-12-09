package dev.mohammadfayaz.sorting.algorithms

import java.util.Random

class RandomList {
  private val random: Random = Random()

  fun integers(size: Int = 20): MutableList<Int> {
    val list: MutableList<Int> = mutableListOf()
    for (i in 0 until size) {
      list.add(random.nextInt())
    }
    return list
  }
}
