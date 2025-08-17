package com.vendifind.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vendifind.data.ThemeOption
import com.vendifind.data.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val themeRepository = ThemeRepository(application)

    private val _themeState = MutableStateFlow(ThemeOption.SYSTEM)
    val themeState = _themeState.asStateFlow()

    init {
        themeRepository.themeOptionFlow
            .onEach { _themeState.value = it }
            .launchIn(viewModelScope)
    }

    fun changeTheme(themeOption: ThemeOption) {
        viewModelScope.launch {
            themeRepository.setThemeOption(themeOption)
        }
    }
}
