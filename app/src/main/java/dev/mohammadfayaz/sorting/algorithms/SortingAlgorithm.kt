package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.algorithms.sorting.BubbleSortImpl
import dev.mohammadfayaz.sorting.algorithms.sorting.InsertionSortImpl
import dev.mohammadfayaz.sorting.algorithms.sorting.MergeSortImpl
import dev.mohammadfayaz.sorting.algorithms.sorting.SelectionSortImpl
import dev.mohammadfayaz.sorting.algorithms.sorting.SortingAlgorithmImpl
import dev.mohammadfayaz.sorting.navigation.Routes

enum class SortingAlgorithm(
  val title: String,
  val route: String,
  val impl: SortingAlgorithmImpl
) {
  BUBBLE_SORT("Bubble Sort", Routes.bubbleSort, impl = BubbleSortImpl()),
  SELECT_SORT("Selection Sort", Routes.selectionSort, impl = SelectionSortImpl()),
  INSERT_SORT("Insertion Sort", Routes.insertionSort, impl = InsertionSortImpl()),
  MERGE_SORT("Merge Sort", Routes.mergeSort, impl = MergeSortImpl()),
//  QUICK_SORT("Quick Sort", Routes.quickSort),
}
