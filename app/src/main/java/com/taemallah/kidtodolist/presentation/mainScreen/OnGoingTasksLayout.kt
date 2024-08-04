package com.taemallah.kidtodolist.presentation.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.taemallah.kidtodolist.domain.LayoutVerticalPadding
import com.taemallah.kidtodolist.presentation.ToDoViewModel

@Composable
fun OnGoingTasksLayout(modifier: Modifier = Modifier, viewModel: ToDoViewModel){
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LayoutVerticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(viewModel.tasks.count()){
            TaskLayout(task = viewModel.tasks[it]) { isChecked ->
                viewModel.tasks[it] = viewModel.tasks[it].copy(isCompleted = isChecked)
            }
        }
    }
}