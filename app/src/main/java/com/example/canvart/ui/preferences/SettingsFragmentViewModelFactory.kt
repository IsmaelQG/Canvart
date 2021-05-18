package com.example.canvart.ui.preferences

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SettingsFragmentViewModelFactory( private val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SettingsFragmentViewModel(sharedPreferences) as T
}