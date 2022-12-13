package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.SLOW
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.FAST
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.MEDIUM

fun SortingSpeed.delayAfterSort(): Long {
  return when (this) {
    SLOW -> 1000
    MEDIUM -> 500
    FAST -> 250
    else -> 0
  }
}

fun SortingSpeed.delayBeforeSort(): Long {
  return when (this) {
    SLOW -> 400
    MEDIUM -> 200
    FAST -> 100
    else -> 0
  }
}

