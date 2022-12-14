package dev.mohammadfayaz.sorting.algorithms.sorting

class InsertionSortImpl : SortingAlgorithmImpl() {
  override suspend fun sort() {
    for (step in 1 until list.size) {
      val key = list[step]
      var j = step - 1

      while (j >= 0 && key.value < list[j].value) {
        list[j + 1] = list[j]
        --j
      }

      list[j + 1] = key
    }
  }
}