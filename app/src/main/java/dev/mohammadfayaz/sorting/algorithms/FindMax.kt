package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.model.SortingItem

class FindMax {
  fun inIntegers(list: List<SortingItem>): Int {
    var max = -999999
    for (i in list) {
      if (i.value > max) {
        max = i.value
      }
    }
    return max
  }
}
