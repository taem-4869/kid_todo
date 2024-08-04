package com.taemallah.kidtodolist.presentation.taskFactoryScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taemallah.kidtodolist.R
import com.taemallah.kidtodolist.domain.CreationLayoutSpacedBy
import com.taemallah.kidtodolist.domain.LayoutHorizontalPadding
import com.taemallah.kidtodolist.domain.MainScreenCorners
import com.taemallah.kidtodolist.domain.RadioGroupBorderWidth
import com.taemallah.kidtodolist.domain.RadioGroupSpacedBy
import com.taemallah.kidtodolist.domain.TaskPriority
import com.taemallah.kidtodolist.domain.dateFormatter
import com.taemallah.kidtodolist.presentation.ToDoViewModel
import com.taemallah.kidtodolist.ui.theme.KIDToDoListTheme
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ToDoViewModel
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = LayoutHorizontalPadding),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.newTask),
            style = MaterialTheme.typography.displaySmall
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(CreationLayoutSpacedBy)
        ) {
            var isDisplayedDatePickerDialog by remember { mutableStateOf(false) }
            var isDisplayedTimePickerDialog by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState(Date().time)
            val timePickerState = rememberTimePickerState(0,0,true)
            //date picker text field and dialog setting
            TextField(
                value = dateFormatter(datePickerState.selectedDateMillis?.let { Date(it) },"dd MMMM yyyy"),
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.due_to))},
                enabled = false,
                colors = TextFieldDefaults.colors(disabledTextColor = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = true) { isDisplayedDatePickerDialog = true }
            )
            if (isDisplayedDatePickerDialog){
                ShowDatePickerDialog(
                    datePickerState,
                    onDismiss = {
                        isDisplayedDatePickerDialog = false
                    }
                ) {
                    isDisplayedDatePickerDialog = false
                }
            }

            //time picker text field and dialog setting
            TextField(
                value = "${timePickerState.hour}:${timePickerState.minute}",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.due_to_time))},
                enabled = false,
                colors = TextFieldDefaults.colors(disabledTextColor = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isDisplayedTimePickerDialog = true }
            )
            if (isDisplayedTimePickerDialog){
                ShowTimePickerDialog(
                    timePickerState = timePickerState,
                    onDismiss = {
                        isDisplayedTimePickerDialog = false
                    }
                ) {
                    isDisplayedTimePickerDialog = false
                }
            }

            //task description text field setting
            var description by remember {
                mutableStateOf("")
            }
            TextField(
                value = description,
                onValueChange = {description = it},
                label = { Text(text = stringResource(id = R.string.description))},
                modifier = Modifier
                    .fillMaxWidth()
            )

            //task priority radiobuttons setting
            var selectedPriority by remember {
                mutableStateOf(TaskPriority.Medium)
            }
            val priorityScrollableState = rememberScrollableState { 0f }
            Column(
                modifier = Modifier
                    .border(
                        width = RadioGroupBorderWidth,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(MainScreenCorners)
                    )
                    .padding(4.dp)
            ) {
                Text(text = stringResource(id = R.string.priority))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(RadioGroupSpacedBy),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .scrollable(priorityScrollableState, Orientation.Horizontal),

                    ) {
                    items(TaskPriority.entries){priority ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedPriority==priority,
                                onClick = { selectedPriority = priority }
                            )
                            Text(text = priority.getPriorityResId())
                        }
                    }
                }
            }

        }
        Button(
            modifier = Modifier
                .fillMaxWidth(.9f),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.save))
        }

/*
        val time = rememberTimePickerState()
        TimePicker(
            modifier = Modifier.fillMaxWidth(),
            state = time
        )
        val datePickerDialog = remDia
            DatePickerDialog(onDismissRequest = {}, confirmButton = {}) {
            val date = rememberDatePickerState(
                Date().time,
                initialDisplayMode = DisplayMode.Input)
            DatePicker(
                modifier = Modifier.fillMaxWidth(),
                state = date
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            var description by remember {
                mutableStateOf("")
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                value = description,
                textStyle = MaterialTheme.typography.titleMedium,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
                onValueChange = {description=it}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            var description by remember {
                mutableStateOf("")
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                value = description,
                textStyle = MaterialTheme.typography.titleMedium,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
                onValueChange = {description=it}
            )
        }
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                }
        )

 */
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePickerDialog(
    datePickerState: DatePickerState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    ) {
        DatePicker(
            modifier = Modifier.fillMaxWidth(),
            state = datePickerState
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimePickerDialog(
    timePickerState: TimePickerState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    ) {
        TimePicker(state = timePickerState)
    }
}

@Composable
@Preview
fun TaskCreationScreenPreview (){
    KIDToDoListTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val viewmodel = ToDoViewModel()
            viewmodel.setTestTasks()
            TaskCreationScreen(
                modifier = Modifier
                    .padding(innerPadding),
                navController = rememberNavController(),
                viewModel = viewmodel
            )
        }
    }

}