package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.SLOW
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.FAST
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.MEDIUM

fun SortingSpeed.delay(): Long {
  return when (this) {
    SLOW -> 2000
    MEDIUM -> 1000
    FAST -> 500
  }
}
