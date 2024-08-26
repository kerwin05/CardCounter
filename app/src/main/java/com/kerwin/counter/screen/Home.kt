package com.kerwin.counter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.blankj.utilcode.util.ToastUtils
import com.kerwin.counter.ui.theme.Black12
import com.kerwin.counter.ui.theme.Black16
import com.kerwin.counter.ui.theme.Black18
import com.kerwin.counter.ui.theme.Black30
import com.kerwin.counter.ui.theme.Purple18
import com.kerwin.counter.ui.theme.Purple30
import com.kerwin.counter.ui.theme.Purple80
import com.kerwin.counter.utils.CardUtil
import com.kerwin.counter.viewmodel.HomeVM


var size by mutableStateOf(Size.Zero) //屏幕大小
const val rows = 3 //分成多少列
const val cols = 5 //分成多少行
var BoxWidth = 0f //小格子的宽
var BoxHeight = 0f //小格子的高

@Preview
@Composable
fun Home() {

    Column(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars)
        .windowInsetsPadding(WindowInsets.navigationBars)
        .onSizeChanged {
            size = it.toSize()
            BoxWidth = size.width / rows
            BoxHeight = size.height / cols
        }) {

        repeat(cols) { col ->
            HomeRow(modifier = Modifier.weight(1f), col)
        }

    }

}

@Composable
fun HomeRow(modifier: Modifier, col: Int) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {

        repeat(rows) { row ->
            HomeBox(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(), col, row
            )
        }

    }

}

@Composable
fun HomeBox(
    modifier: Modifier,
    col: Int,
    row: Int
) {

    val text = CardUtil.showTextForColRow(col, row)
    val initialCount = HomeVM.initialCount(text)
    val count = HomeVM.gridState.value[col][row]

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = if (count.intValue == 0) Color.Black else Color.Transparent)
        .border(width = 1.dp, color = Purple80)
        .clickable {
            if (CardUtil.isCard(text)) {
                HomeVM.clickRefresh()
            } else {
                count.intValue = if (count.intValue > 0) count.intValue - 1 else 0
            }
        }) {
        if (!CardUtil.isCard(text))
            Remaining(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp),
                count = count.intValue
            )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = if (count.intValue == 0) Purple30 else Black30
        )

        if (!CardUtil.isCard(text))
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                onClick = {
                    count.intValue =
                        if (count.intValue < initialCount) count.intValue + 1 else initialCount
                }) {

                Text(text = "+1", style = Black18)

            }

    }

}

@Composable
fun Remaining(modifier: Modifier = Modifier, count: Int) {
    Row(modifier = modifier) {
        repeat(count) {
            Box(
                modifier = Modifier
                    .size(16.dp) // 设置圆点的大小
                    .background(color = Purple80, shape = CircleShape) // 设置圆点的颜色和形状
            )

            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}