package com.taemallah.kidtodolist.presentation.mainScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.taemallah.kidtodolist.R
import com.taemallah.kidtodolist.domain.LayoutVerticalPadding2
import com.taemallah.kidtodolist.domain.MainScreenCorners
import com.taemallah.kidtodolist.domain.SpacerHigh
import com.taemallah.kidtodolist.domain.Task
import com.taemallah.kidtodolist.domain.TaskInnerSpacing
import com.taemallah.kidtodolist.domain.dateFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskLayout(
    modifier: Modifier = Modifier,
    task: Task,
    onCheck : (isChecked : Boolean) -> Unit,
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                2.dp,
                RoundedCornerShape(MainScreenCorners),
                ambientColor = task.priority.color,
                spotColor = task.priority.color
            )
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(MainScreenCorners)
            )
            .padding(LayoutVerticalPadding2, LayoutVerticalPadding2)
            .combinedClickable(
                onClick = {},
                onDoubleClick = {},
                onLongClick = {}
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth(.9f)
            .height(SpacerHigh)
            .background(task.priority.color, RoundedCornerShape(40))
        )
        Row (
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(//task details column
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(TaskInnerSpacing)
            ) {
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Text(
                    text = dateFormatter(task.dueTo),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Column (//task options column
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(//options drop down button
                    painter = painterResource(id = R.drawable.arrow_drop_down),
                    contentDescription = stringResource(R.string.task_options_button),
                    modifier = Modifier.clickable { /* TODO:  */ }
                )

                var isDone by remember {
                    mutableStateOf(task.isCompleted)
                }
                Checkbox(//is done checkbox
                    checked = isDone,
                    onCheckedChange = {
                        isDone = !isDone
                        onCheck(isDone)
                    }
                )
            }
        }
    }
}