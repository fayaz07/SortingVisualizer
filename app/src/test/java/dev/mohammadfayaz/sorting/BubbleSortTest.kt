package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.BubbleSort
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BubbleSortTest {

  private val bubbleSort: BubbleSort = BubbleSort()

  @Test
  fun simple_test() = runBlocking {
    bubbleSort.list = mutableListOf(7, 9, 12, 4, 3, 5)
    bubbleSort.sort()
    assert(bubbleSort.list == mutableListOf(3, 4, 5, 7, 9, 12))
  }
}
