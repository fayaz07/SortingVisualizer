package dev.mohammadfayaz.sorting.algorithms.sorting

import dev.mohammadfayaz.sorting.model.SortingItem
import dev.mohammadfayaz.sorting.model.SortingItemState

class MergeSortImpl : SortingAlgorithmImpl() {
  override suspend fun sort() {
    mergeSort(list, 0, list.size - 1)
  }

  private fun merge(arr: MutableList<SortingItem>, p: Int, q: Int, r: Int) {

    // Create L ← A[p..q] and M ← A[q+1..r]
    val n1 = q - p + 1
    val n2 = r - q
    val leftList = MutableList(n1) { SortingItem(-1, SortingItemState.IDLE) }
    val mergingList = MutableList(n2) { SortingItem(-1, SortingItemState.IDLE) }

    for (i in 0 until n1) leftList[i] = arr[p + i]
    for (j in 0 until n2) mergingList[j] = arr[q + 1 + j]

    // Maintain current index of sub-arrays and main array
    var i = 0
    var j = 0
    var k = p

    // Until we reach either end of either L or M, pick larger among
    // elements L and M and place them in the correct position at A[p..r]
    while (i < n1 && j < n2) {
      if (leftList[i].value <= mergingList[j].value) {
        arr[k] = leftList[i]
        i++
      } else {
        arr[k] = mergingList[j]
        j++
      }
      k++
    }

    // When we run out of elements in either L or M,
    // pick up the remaining elements and put in A[p..r]
    while (i < n1) {
      arr[k] = leftList[i]
      i++
      k++
    }
    while (j < n2) {
      arr[k] = mergingList[j]
      j++
      k++
    }
  }

  // Divide the array into two subarrays, sort them and merge them
  private suspend fun mergeSort(arr: MutableList<SortingItem>, l: Int, r: Int) {
    if (l < r) {

      // m is the point where the array is divided into two subarrays
      val m = (l + r) / 2
      mergeSort(arr, l, m)
      mergeSort(arr, m + 1, r)

      // Merge the sorted subarrays
      merge(arr, l, m, r)

      listFlow.send(list)
    }
  }
}
