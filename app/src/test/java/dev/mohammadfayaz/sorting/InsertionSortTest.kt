package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.algorithms.sorting.InsertionSortImpl
import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertionSortTest {
  private val insertionSort = InsertionSortImpl()

  @Before
  fun setup() {
    insertionSort.speed = SortingSpeed.NO_DELAY
  }

  @Test
  fun simple_test() = runBlocking {
    val list = insertionSort.genRandom(5)

    println("List-> $list")
    var finalList = mutableListOf<SortingItem>()
    val job = launch {
      insertionSort.listFlow.consumeAsFlow().collect {
        println("sort -> $it")
        finalList = it as MutableList<SortingItem>
      }
    }
    insertionSort.sort()
    assert(finalList == list.sortedBy { e-> e.value })
    job.cancel()
  }
}
