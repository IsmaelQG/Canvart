package com.example.canvart.ui.challenges

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.canvart.R
import com.example.canvart.databinding.FragmentChallengesMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.canvart.utils.viewBinding

class ChallengesMenuFragment : Fragment(R.layout.fragment_challenges_menu) {

    private val binding by viewBinding { FragmentChallengesMenuBinding.bind(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        binding.level = sharedPreferences.getInt("userLevel", 0)
        setupToolbar()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        listeners()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.app_name)
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun listeners(){
        binding.cntImageOverlay.setOnClickListener {
            goToChallenge()
        }
        binding.cntPortraitOverlay.setOnClickListener {
            goToChallenge()
        }
        binding.cntDescriptionOverlay.setOnClickListener {
            goToChallenge()
        }
    }

    private fun goToChallenge(){
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fcDetail, ImageChallengeFragment.newInstance())
                addToBackStack("")
            }
    }

    private fun goBack(): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return true
    }

    companion object{

        fun newInstance() : ChallengesMenuFragment =
            ChallengesMenuFragment()

    }
}