package dev.mohammadfayaz.sorting.ui.sorting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarComposable(maxOffset: Int, offset: Int, maxWidth: Int) {
  val maxOffsetF = maxOffset.toFloat()
  val offsetF = offset.toFloat()
  val maxWidthF = maxWidth.toFloat()
  val percentage: Float = 1 - (maxOffsetF.minus(offsetF) / maxOffsetF)
  val width = maxWidthF * percentage

  Box(
    modifier = Modifier
      .padding(top = 2.dp, bottom = 2.dp)
      .size(
        width = maxWidth.dp,
        height = 32.dp
      )
      .background(Color.White),
    contentAlignment = Alignment.Center
  ) {
    Box(
      modifier = Modifier
        .padding(top = 2.dp, bottom = 2.dp)
        .size(
          width = width.dp,
          height = 32.dp
        )
        .background(Color.Cyan)
        .align(Alignment.CenterStart)
    )
    Text(text = "$offset")
  }
}

@Preview
@Composable
private fun BarComposablePreview() {
  val configuration = LocalConfiguration.current

  val screenWidth = configuration.screenWidthDp - 32

  Column(
    modifier = Modifier.width(screenWidth.dp)
  ) {
    BarComposable(maxOffset = 100, offset = 100, screenWidth)
    BarComposable(maxOffset = 100, offset = 60, screenWidth)
    BarComposable(maxOffset = 100, offset = 75, screenWidth)
    BarComposable(maxOffset = 100, offset = 90, screenWidth)
    BarComposable(maxOffset = 100, offset = 23, screenWidth)
    BarComposable(maxOffset = 100, offset = 50, screenWidth)
    BarComposable(maxOffset = 100, offset = 40, screenWidth)
    BarComposable(maxOffset = 100, offset = 25, screenWidth)
  }
}
