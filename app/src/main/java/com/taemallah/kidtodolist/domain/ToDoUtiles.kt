package com.taemallah.kidtodolist.domain

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val AppBarSpacing : Dp = 16.dp
val MainScreenSpacing : Dp = 20.dp
const val MainScreenCorners : Int = 8
val LayoutHorizontalPadding : Dp = 15.dp
val LayoutHorizontalPadding2 : Dp = 10.dp
val LayoutVerticalPadding : Dp = 7.5.dp
val LayoutVerticalPadding2 : Dp = 8.dp
val TaskInnerSpacing : Dp = 5.dp
val SpacerPadding : Dp = 10.dp
val SpacerHigh : Dp = 2.dp

val CreationLayoutSpacedBy : Dp = 20.dp
val RadioGroupSpacedBy : Dp = 15.dp
val RadioGroupBorderWidth : Dp = 1.dp
val FabPadding : Dp = 10.dp

fun dateFormatter(date : Date? = Date(), format : String = "dd MMMM yyyy  hh:mma") : String{
    if (date==null) return ""
    return SimpleDateFormat(format, Locale.getDefault()).format(date)
}