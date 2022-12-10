package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.BubbleSortImpl
import dev.mohammadfayaz.sorting.algorithms.RandomList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BubbleSortTest {

  private val bubbleSort: BubbleSortImpl = BubbleSortImpl()
  private val randomList: RandomList = RandomList()

  @Test
  fun simple_test() = runBlocking {
    bubbleSort.list = mutableListOf(7, 9, 12, 4, 3, 5)
    bubbleSort.sort()
    assert(bubbleSort.list == mutableListOf(3, 4, 5, 7, 9, 12))
  }

  @Test
  fun run_on_random_list() = runBlocking {
    val initialList = randomList.integers(30)
    val sortedList = initialList.sorted()
    bubbleSort.list = initialList
    bubbleSort.sort()
    assert(bubbleSort.list == sortedList)
  }

  @Test
  fun run_on_random_list_huge_size() = runBlocking {
    val initialList = randomList.integers(300)
    val sortedList = initialList.sorted()
    bubbleSort.list = initialList
    bubbleSort.sort()
    assert(bubbleSort.list == sortedList)
  }
}
