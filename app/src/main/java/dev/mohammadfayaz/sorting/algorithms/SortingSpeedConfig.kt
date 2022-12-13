package dev.mohammadfayaz.sorting.algorithms

import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.SLOW
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.FAST
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed.MEDIUM

fun SortingSpeed.delay(): Long {
  return when (this) {
    SLOW -> 1000
    MEDIUM -> 500
    FAST -> 250
  }
}
