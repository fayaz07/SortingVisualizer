package dev.mohammadfayaz.sorting.model

data class SortingItem(
  val value: Int,
  var state: SortingItemState
) {
  override fun toString(): String {
    return "{ $value: $state }"
  }

  override fun equals(other: Any?): Boolean {
    other as SortingItem
    return this.value == other.value && this.state == other.state
  }

  override fun hashCode(): Int {
    var result = value
    result = 31 * result + state.hashCode()
    return result
  }
}
