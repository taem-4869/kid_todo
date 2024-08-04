package com.taemallah.kidtodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taemallah.kidtodolist.presentation.mainScreen.MainScreen
import com.taemallah.kidtodolist.presentation.ToDoViewModel
import com.taemallah.kidtodolist.presentation.taskFactoryScreen.TaskCreationScreen
import com.taemallah.kidtodolist.ui.theme.KIDToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KIDToDoListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val viewmodel = ToDoViewModel()
                    viewmodel.setTestTasks()
                    NavHost(navController = navController, startDestination = Routes.MainScreen) {
                        composable(Routes.MainScreen){
                            MainScreen(
                                Modifier
                                    .background(MaterialTheme.colorScheme.surfaceContainer)
                                    .padding(innerPadding),
                                navController,
                                viewModel = viewmodel)
                        }
                        composable(Routes.TaskCreationScreen){
                            TaskCreationScreen(
                                modifier = Modifier
                                    .padding(innerPadding),
                                navController,
                                viewModel = viewmodel
                            )
                        }
                    }
                }
            }
        }
    }
}
