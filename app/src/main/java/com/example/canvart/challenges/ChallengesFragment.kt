package com.example.canvart.challenges

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.canvart.R
import com.example.canvart.databinding.FragmentChallengesBinding
import es.iessaladillo.pedrojoya.tasks_app.utils.viewBinding

class ChallengesFragment : Fragment(R.layout.fragment_challenges) {

    private val binding by viewBinding { FragmentChallengesBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.app_name)
            inflateMenu(R.menu.menu_list_challenges)
            setNavigationIcon(R.drawable.ic_info_light)
        }
    }

}