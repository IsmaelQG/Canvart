package com.example.canvart.ui.adventure

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.canvart.R
import com.example.canvart.databinding.FragmentAdventureBinding
import com.example.canvart.ui.tutorial.TutorialFragment
import com.example.canvart.utils.viewBinding

class AdventureFragment : Fragment(R.layout.fragment_adventure) {

    private val binding by viewBinding { FragmentAdventureBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.adventure_text)
            inflateMenu(R.menu.menu_list_adventure)
            setNavigationIcon(R.drawable.ic_info_dark)
            setOnMenuItemClickListener { onMenuItemClick(it) }
        }
    }

    private fun setupViews(){

    }

    private fun onMenuItemClick(item : MenuItem) : Boolean{
        when(item.itemId){
            R.id.mnuTutorial -> navigateToTutorial()
            else -> return false
        }
        return true
    }
    private fun navigateToTutorial(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcDetail, TutorialFragment.newInstance())
            addToBackStack("")
        }
    }

    companion object{

        fun newInstance() : AdventureFragment =
                AdventureFragment()

    }

}