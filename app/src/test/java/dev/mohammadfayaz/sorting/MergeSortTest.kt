package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.algorithms.sorting.MergeSortImpl
import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MergeSortTest{
  private val mergeSort = MergeSortImpl()

  @Before
  fun setup() {
    mergeSort.speed = SortingSpeed.NO_DELAY
  }

  @Test
  fun simple_test() = runBlocking {
    val list = mergeSort.genRandom(5)

    println("List-> $list")
    var finalList = mutableListOf<SortingItem>()
    val job = launch {
      mergeSort.listFlow.consumeAsFlow().collect {
        println("sort -> $it")
        finalList = it as MutableList<SortingItem>
      }
    }
    mergeSort.sort()
    assert(finalList == list.sortedBy { e-> e.value })
    job.cancel()
  }
}
