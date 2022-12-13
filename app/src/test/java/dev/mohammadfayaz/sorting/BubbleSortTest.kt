package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.BubbleSortImpl
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem
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

  @Test
  fun large_set_test() = runBlocking {
    val list = bubbleSort.genRandom(300)

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
}
