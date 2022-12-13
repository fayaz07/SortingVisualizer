package dev.mohammadfayaz.sorting.model

import androidx.compose.ui.graphics.Color
import dev.mohammadfayaz.sorting.ui.theme.idle
import dev.mohammadfayaz.sorting.ui.theme.selected
import dev.mohammadfayaz.sorting.ui.theme.swapping

enum class SortingItemState(val color: Color) {
  IDLE(color = idle),
  SELECTED(color = selected),
  SWAPPING(color = swapping)
}
