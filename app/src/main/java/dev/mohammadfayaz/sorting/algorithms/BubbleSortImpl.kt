package dev.mohammadfayaz.sorting.algorithms

import kotlinx.coroutines.delay

class BubbleSortImpl : SortingAlgorithmImpl() {
  override suspend fun genRandom(count: Int): MutableList<Int> {
    val randomIntegers = RandomList().integers(count)
    list = randomIntegers
    return list
  }

  override suspend fun sort() {
    for (i in 0..list.size) {
      for (j in (i + 1) until list.size) {
        if (list[i] > list[j]) {
          // swap both of them
          val x = list[j]
          list[j] = list[i]
          list[i] = x
        }
      }
      listFlow.send(list)
      delay(speed.delay())
    }
  }
}
