package com.taemallah.kidtodolist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.taemallah.kidtodolist.domain.Task
import com.taemallah.kidtodolist.domain.testTasks

class ToDoViewModel : ViewModel() {
    var tasks = mutableStateListOf<Task>()
    fun setTestTasks(){
        tasks.addAll(testTasks)
    }
}

