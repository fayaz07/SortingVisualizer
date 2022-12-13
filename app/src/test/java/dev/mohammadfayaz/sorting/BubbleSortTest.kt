package dev.mohammadfayaz.sorting

import android.util.Log
import dev.mohammadfayaz.sorting.algorithms.BubbleSortImpl
import dev.mohammadfayaz.sorting.algorithms.RandomList
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class BubbleSortTest {

  private val bubbleSort: BubbleSortImpl = BubbleSortImpl()

  @Before
  fun setup() {
    bubbleSort.speed = SortingSpeed.NO_DELAY
  }

  @Test
  fun simple_test() = runBlocking {
    val list = bubbleSort.genRandom(5)

    println("List-> $list")

    var finalList = mutableListOf<SortingItem>()
    val job = launch {
      bubbleSort.listFlow.consumeAsFlow().collect {
        println("sort -> $it")
        finalList = it as MutableList<SortingItem>
      }
    }
    bubbleSort.sort()
    assert(finalList == list.sortedBy { e-> e.value })
    job.cancel()
  }

//  @Test
//  fun run_on_random_list() = runBlocking {
//    val initialList = randomList.integers(30)
//    val sortedList = initialList.sorted()
//    bubbleSort.list = initialList
//    bubbleSort.sort()
//    assert(bubbleSort.list == sortedList)
//  }
//
//  @Test
//  fun run_on_random_list_huge_size() = runBlocking {
//    val initialList = randomList.integers(300)
//    val sortedList = initialList.sorted()
//    bubbleSort.list = initialList
//    bubbleSort.sort()
//    assert(bubbleSort.list == sortedList)
//  }
}
