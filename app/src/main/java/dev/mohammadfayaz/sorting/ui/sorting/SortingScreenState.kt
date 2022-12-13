package dev.mohammadfayaz.sorting.ui.sorting

import dev.mohammadfayaz.sorting.algorithms.SortingAlgorithm
import dev.mohammadfayaz.sorting.algorithms.SortingSpeed

data class SortingScreenState(
  val count: String,
  val speed: SortingSpeed,
  val sortingAlgorithm: SortingAlgorithm,
  val max: Int = -9999
)
