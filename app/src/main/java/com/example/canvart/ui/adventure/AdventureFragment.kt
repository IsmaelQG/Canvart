package com.example.canvart.ui.adventure

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.canvart.R
import com.example.canvart.databinding.FragmentAdventureBinding
import es.iessaladillo.pedrojoya.tasks_app.utils.viewBinding

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
            setNavigationIcon(R.drawable.ic_info_light)
        }
    }

    private fun setupViews(){

    }

    companion object{

        fun newInstance() : AdventureFragment =
                AdventureFragment()

    }

}