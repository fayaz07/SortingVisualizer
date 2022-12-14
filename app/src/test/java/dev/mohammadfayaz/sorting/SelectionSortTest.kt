package dev.mohammadfayaz.sorting

import dev.mohammadfayaz.sorting.algorithms.sorting.SelectionSortImpl
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed
import dev.mohammadfayaz.sorting.model.SortingItem
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SelectionSortTest {
  private val selectionSort = SelectionSortImpl()

  @Before
  fun setup() {
    selectionSort.speed = SortingSpeed.NO_DELAY
  }

  @Test
  fun simple_test() = runBlocking {
    val list = selectionSort.genRandom(5)

    println("List-> $list")
    var finalList = mutableListOf<SortingItem>()
    val job = launch {
      selectionSort.listFlow.consumeAsFlow().collect {
        println("sort -> $it")
        finalList = it as MutableList<SortingItem>
      }
    }
    selectionSort.sort()
    assert(finalList == list.sortedBy { e-> e.value })
    job.cancel()
  }
}
