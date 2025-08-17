package com.vendifind.view

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vendifind.data.ThemeOption
import com.vendifind.viewmodel.MainViewModel

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val themeOption by mainViewModel.themeState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Select Theme", modifier = Modifier.padding(bottom = 8.dp))
        ThemeOption.entries.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == themeOption),
                        onClick = { mainViewModel.changeTheme(option) }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == themeOption),
                    onClick = { mainViewModel.changeTheme(option) }
                )
                Text(
                    text = option.name,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
