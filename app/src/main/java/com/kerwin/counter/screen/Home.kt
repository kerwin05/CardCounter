package com.kerwin.counter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.kerwin.counter.ui.theme.Black18
import com.kerwin.counter.ui.theme.Black30
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
    val count = HomeVM.gridState.value[col][row]

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = if (count.intValue == 0) Purple80 else Color.Transparent)
        .border(width = 1.dp, color = Color.Black)
        .clickable {
            if (CardUtil.isCard(text)) {
                HomeVM.clickRefresh()
            } else {
                count.intValue = if (count.intValue > 0) count.intValue - 1 else 0
            }
        }) {
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(6.dp),
            text = if (CardUtil.isCard(text)) "" else "剩余:${count.intValue}",
            style = Black18
        )

        Text(modifier = Modifier.align(Alignment.Center), text = text, style = Black30)

        if (!CardUtil.isCard(text))
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                onClick = {
                    count.intValue = if (count.intValue < 4) count.intValue + 1 else 4
                }) {

                Text(text = "+1", style = Black18)

            }

    }

}