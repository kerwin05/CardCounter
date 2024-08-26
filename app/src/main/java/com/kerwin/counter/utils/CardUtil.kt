package com.kerwin.counter.utils

import com.blankj.utilcode.util.LogUtils

object CardUtil {

    fun showTextForColRow(col: Int, row: Int): String {
        LogUtils.d("CardUtil col:$col,row:$row")
        return when(col) {
            0 -> when(row) {
                0 -> "重置"
                1 -> ""
                2 -> "2"
                else -> ""
            }
            1 -> when(row) {
                0 -> "A"
                1 -> "K"
                2 -> "Q"
                else -> ""
            }
            2 -> when(row) {
                0 -> "J"
                1 -> "10"
                2 -> "9"
                else -> ""
            }
            3 -> when(row) {
                0 -> "8"
                1 -> "7"
                2 -> "6"
                else -> ""
            }
            4 -> when(row) {
                0 -> "5"
                1 -> "4"
                2 -> "3"
                else -> ""
            }
            else -> ""
        }
    }

    fun isCard(text: String): Boolean = text == "重置" || text == ""

}