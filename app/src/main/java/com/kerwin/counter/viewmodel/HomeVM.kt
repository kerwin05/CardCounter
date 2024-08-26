package com.kerwin.counter.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.kerwin.counter.screen.cols
import com.kerwin.counter.screen.rows
import com.kerwin.counter.utils.CardUtil

object HomeVM {

    val initialCount = { text: String ->
        when (text) {
            "2" -> 1
            "A" -> 3
            else -> 4
        }
    }

    val gridState = mutableStateOf(
        Array(cols) { col ->
            Array(rows) { row ->
                mutableIntStateOf(initialCount(CardUtil.showTextForColRow(col, row)))
            }
        }
    )

    fun clickRefresh() {
        repeat(cols) { col ->
            repeat(rows) { row ->
                gridState.value[col][row].intValue = initialCount(CardUtil.showTextForColRow(col, row))
            }
        }
    }

}