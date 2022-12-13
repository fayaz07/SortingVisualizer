package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.SLOW
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.FAST
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.MEDIUM

fun SortingSpeed.delayAfterSort(): Long {
  return when (this) {
    SLOW -> 1000
    MEDIUM -> 300
    FAST -> 200
    else -> 0
  }
}

fun SortingSpeed.delayBeforeSort(): Long {
  return when (this) {
    SLOW -> 200
    MEDIUM -> 100
    FAST -> 50
    else -> 0
  }
}

