package dev.mohammadfayaz.sorting.algorithms

class BubbleSort : SortingAlgorithm() {
  override suspend fun sort() {
    for (i in 0..list.size) {
      for (j in (i + 1) until list.size) {
        if (list[i] > list[j]) {
          // swap both of them
          val x = list[j]
          list[j] = list[i]
          list[i] = x
        }
      }
    }
    _listFlow.emit(list)
  }

  override fun name(): String {
    return "Bubble Sort"
  }
}
