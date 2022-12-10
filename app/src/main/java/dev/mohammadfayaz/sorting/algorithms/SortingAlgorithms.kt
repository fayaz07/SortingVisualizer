package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.navigation.Routes

enum class SortingAlgorithms(
  val title: String,
  val route: String
) {
  BUBBLE_SORT("Bubble Sort", Routes.bubbleSort),
  SELECT_SORT("Selection Sort", Routes.selectionSort),
  INSERT_SORT("Insertion Sort", Routes.insertionSort),
  MERGE_SORT("Merge Sort", Routes.mergeSort),
  QUICK_SORT("Quick Sort", Routes.quickSort),
}
