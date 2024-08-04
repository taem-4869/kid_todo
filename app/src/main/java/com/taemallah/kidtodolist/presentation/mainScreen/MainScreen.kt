package com.taemallah.kidtodolist.presentation.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.taemallah.kidtodolist.R
import com.taemallah.kidtodolist.Routes
import com.taemallah.kidtodolist.domain.FabPadding
import com.taemallah.kidtodolist.domain.LayoutHorizontalPadding
import com.taemallah.kidtodolist.domain.LayoutVerticalPadding
import com.taemallah.kidtodolist.domain.MainScreenSpacing
import com.taemallah.kidtodolist.presentation.ToDoViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ToDoViewModel
){
    Box(modifier = modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LayoutHorizontalPadding, vertical = LayoutVerticalPadding),
            verticalArrangement = Arrangement.spacedBy(MainScreenSpacing)
        ){
            ToDoAppBar()
            TodayTasksLayout(viewModel = viewModel)
            OnGoingTasksLayout(viewModel = viewModel)
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(FabPadding)
                .align(Alignment.BottomEnd)
                .alpha(.7f),
            onClick = { navController.navigate(Routes.TaskCreationScreen) }
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.new_task_button))
        }
    }
}