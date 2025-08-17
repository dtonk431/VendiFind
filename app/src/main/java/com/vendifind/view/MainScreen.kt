package com.vendifind.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vendifind.viewmodel.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    Text(text = "Hello from MainScreen!")
}
