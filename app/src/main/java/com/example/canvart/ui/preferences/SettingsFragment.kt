package com.example.canvart.ui.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.canvart.R
import com.example.canvart.databinding.FragmentSettingsBinding
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding { FragmentSettingsBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews() }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        binding.level = sharedPreferences.getInt("userLevel", 0)
        when(sharedPreferences.getInt("difficulty", -1)){
            0 -> binding.rdbEasySettings.isChecked = true
            1 -> binding.rdbMediumSettings.isChecked = true
            2 -> binding.rdbHardSettings.isChecked = true
        }
        listeners()
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.settings_title)
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun listeners(){
        binding.rdgDifficulty.setOnCheckedChangeListener { group, checkedId ->
            changeCheckedDifficulty(checkedId)
        }
    }

    private fun changeCheckedDifficulty(id : Int){
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        when(id){
            R.id.rdbEasySettings -> sharedPreferences.edit().putInt("difficulty", 0).apply()
            R.id.rdbMediumSettings -> sharedPreferences.edit().putInt("difficulty", 1).apply()
            R.id.rdbHardSettings -> sharedPreferences.edit().putInt("difficulty", 2).apply()
        }
    }

    private fun goBack(): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return true
    }

}