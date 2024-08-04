package com.taemallah.kidtodolist.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.taemallah.kidtodolist.R
import java.util.Date

data class Task(
    var description : String = "",
    var dueTo : Date? = null,
    var priority : TaskPriority,
    var isCompleted : Boolean = false
)

enum class TaskPriority(val color:Color){
    Low(Color.Green),
    Medium(Color.Yellow),
    High(Color(255,165,0)),
    Critical(Color.Red);
    @Composable
    fun getPriorityResId(): String {
        return when(this){
            Low -> stringResource(id = R.string.low)
            Medium -> stringResource(id = R.string.medium)
            High -> stringResource(id = R.string.high)
            Critical -> stringResource(id = R.string.critical)
        }
    }
}

val testTasks = listOf(
    Task(
        description = "Buy groceries",
        dueTo = null,
        priority = TaskPriority.Medium,
        isCompleted = false
    ),
    Task(
        description = "Finish Android project",
        dueTo = Date(2024, 7, 30),
        priority = TaskPriority.High,
        isCompleted = false
    ),
    Task(
        description = "Call the bank",
        dueTo = Date(2024, 7, 28),
        priority = TaskPriority.Critical,
        isCompleted = false
    ),
    Task(
        description = "Clean the house",
        dueTo = null,
        priority = TaskPriority.Low,
        isCompleted = false
    ),
    Task(
        description = "Write blog post",
        dueTo = Date(2024, 8, 1),
        priority = TaskPriority.Medium,
        isCompleted = false
    ),
    Task(
        description = "Prepare for meeting",
        dueTo = Date(2024, 7, 27),
        priority = TaskPriority.Critical,
        isCompleted = true
    ),
    Task(
        description = "Exercise",
        dueTo = null,
        priority = TaskPriority.Low,
        isCompleted = false
    ),
    Task(
        description = "Send email to client",
        dueTo = Date(2024, 7, 26),
        priority = TaskPriority.High,
        isCompleted = false
    ),
    Task(
        description = "Water the plants",
        dueTo = null,
        priority = TaskPriority.Low,
        isCompleted = true
    ),
    Task(
        description = "Plan vacation",
        dueTo = Date(2024, 8, 15),
        priority = TaskPriority.Medium,
        isCompleted = false
    )
)