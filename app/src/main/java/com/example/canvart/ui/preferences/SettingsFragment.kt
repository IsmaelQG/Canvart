package com.example.canvart.ui.preferences

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.canvart.R
import com.example.canvart.databinding.FragmentSettingsBinding
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding { FragmentSettingsBinding.bind(it)}

    private val viewModel : SettingsFragmentViewModel by activityViewModels(){
        SettingsFragmentViewModelFactory(
            requireActivity().getPreferences(Context.MODE_PRIVATE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        listeners()
        observers()
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
            changeChecked(checkedId)
        }
        binding.rdgMaterial.setOnCheckedChangeListener { group, checkedId ->
            changeChecked(checkedId)
        }
        binding.rdgTimer.setOnCheckedChangeListener { group, checkedId ->
            changeChecked(checkedId)
        }
    }

    private fun observers(){
        viewModel.userLevelLiveData.observe(viewLifecycleOwner, Observer {
            result -> binding.level = result
        })
        viewModel.difficultyLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            when(result){
                0 -> binding.rdbEasySettings.isChecked = true
                1 -> binding.rdbMediumSettings.isChecked = true
                2 -> binding.rdbHardSettings.isChecked = true
            }
        })
        viewModel.materialLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            when(result){
                0 -> binding.rdbPencilSettings.isChecked = true
                1 -> binding.rdbPenSettings.isChecked = true
                2 -> binding.rdbMarkerSettings.isChecked = true
            }
        })
        viewModel.timerLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            when(result){
                0 -> binding.rdbOneMinuteSettings.isChecked = true
                1 -> binding.rdbTwoMinutesSettings.isChecked = true
                2 -> binding.rdbFiveMinutesSettings.isChecked = true
                3 -> binding.rdbTenMinutesSettings.isChecked = true
                4 -> binding.rdbFifteenMinutesSettings.isChecked = true
                5 -> binding.rdbInfMinutesSettings.isChecked = true
            }
        })
    }

    private fun changeChecked(id : Int){
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        when(id){
            R.id.rdbEasySettings -> sharedPreferences.edit().putInt("difficulty", 0).apply()
            R.id.rdbMediumSettings -> sharedPreferences.edit().putInt("difficulty", 1).apply()
            R.id.rdbHardSettings -> sharedPreferences.edit().putInt("difficulty", 2).apply()

            R.id.rdbPencilSettings -> sharedPreferences.edit().putInt("material", 0).apply()
            R.id.rdbPenSettings -> sharedPreferences.edit().putInt("material", 1).apply()
            R.id.rdbMarkerSettings -> sharedPreferences.edit().putInt("material", 2).apply()

            R.id.rdbOneMinuteSettings -> sharedPreferences.edit().putInt("timer", 0).apply()
            R.id.rdbTwoMinutesSettings -> sharedPreferences.edit().putInt("timer", 1).apply()
            R.id.rdbFiveMinutesSettings -> sharedPreferences.edit().putInt("timer", 2).apply()
            R.id.rdbTenMinutesSettings -> sharedPreferences.edit().putInt("timer", 3).apply()
            R.id.rdbFifteenMinutesSettings -> sharedPreferences.edit().putInt("timer", 4).apply()
            R.id.rdbInfMinutesSettings -> sharedPreferences.edit().putInt("timer", 5).apply()
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