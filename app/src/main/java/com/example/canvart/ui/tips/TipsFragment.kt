package com.example.canvart.ui.tips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.canvart.R
import com.example.canvart.databinding.FragmentTipsBinding
import com.example.canvart.utils.viewBinding

class TipsFragment : Fragment(R.layout.fragment_tips) {

    private val binding by viewBinding { FragmentTipsBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.tips_text)
            inflateMenu(R.menu.menu_list_tips)
            setNavigationIcon(R.drawable.ic_info_light)
        }
    }

    companion object{

        fun newInstance() : TipsFragment =
            TipsFragment()

    }

}