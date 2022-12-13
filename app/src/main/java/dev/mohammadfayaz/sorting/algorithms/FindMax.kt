package dev.mohammadfayaz.sorting.algorithms

class FindMax {
  fun inIntegers(list: List<Int>): Int {
    var max = -999999;
    for (i in list) {
      if (i > max) {
        max = i
      }
    }
    return max
  }
}
