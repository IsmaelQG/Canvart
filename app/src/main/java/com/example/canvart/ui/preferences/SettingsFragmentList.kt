package com.example.canvart.ui.preferences

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.example.canvart.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragmentList : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }
}