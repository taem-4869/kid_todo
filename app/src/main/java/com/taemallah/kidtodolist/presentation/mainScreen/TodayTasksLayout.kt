package com.taemallah.kidtodolist.presentation.mainScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.taemallah.kidtodolist.R
import com.taemallah.kidtodolist.domain.LayoutHorizontalPadding2
import com.taemallah.kidtodolist.domain.LayoutVerticalPadding2
import com.taemallah.kidtodolist.domain.MainScreenCorners
import com.taemallah.kidtodolist.presentation.ToDoViewModel

@Composable
fun TodayTasksLayout(modifier: Modifier = Modifier, viewModel: ToDoViewModel){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp,RoundedCornerShape(MainScreenCorners))
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(MainScreenCorners)
            )
            .padding(LayoutHorizontalPadding2, LayoutVerticalPadding2),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.todays_tasks),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        val progress = viewModel.tasks.count { it.isCompleted }/viewModel.tasks.count().toFloat()
        val progressAnimation by animateFloatAsState(targetValue = progress, label = "")
        CircularProgressIndicator(
            trackColor = MaterialTheme.colorScheme.secondaryContainer,
            progress = {
                progressAnimation
            }
        )
    }
}