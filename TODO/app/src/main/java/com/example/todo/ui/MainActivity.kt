package com.example.todo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.ui.theme.TODOTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.model.Todo
import com.example.todo.viewmodel.TodoUiState
import com.example.todo.viewmodel.TodoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOTheme {
                TodoApp()
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(todoViewModel: TodoViewModel = viewModel()) {
    Scaffold (
        topBar = {
            TopAppBar(
                title={Text("Todos")}
            )
        }
    )
    { innerPadding ->
        TodoScreen(Modifier.padding(innerPadding),todoViewModel.todoUiState)
    }
}

@Composable
fun TodoScreen(modifier: Modifier, uiState: TodoUiState) {
    when (uiState) {
        is TodoUiState.Loading -> LoadingScreen()
        is TodoUiState.Success -> TodoList(modifier,uiState.todos)
        is TodoUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Text("Loading...")
}

@Composable
fun ErrorScreen() {
    Text("Error retrieving data from API.")
}

@Composable
fun TodoList(modifier: Modifier = Modifier, todos: List<Todo>) {
    LazyColumn  (
        modifier = Modifier.padding(8.dp)
    )
    {
        items(todos) { todo ->
            Text(
                text = todo.title,
                modifier = Modifier.padding(top=4.dp, bottom=4.dp)
            )
            HorizontalDivider(color = Color.LightGray, thickness = 2.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoPreview() {
    TODOTheme {
        TodoApp()
    }
}