package com.example.canvart.ui.challenges.challengesMenu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.canvart.R
import com.example.canvart.databinding.FragmentChallengesMenuBinding
import com.example.canvart.ui.challenges.descriptionChallenge.DescriptionChallengeFragment
import com.example.canvart.ui.challenges.imageChallenge.ImageChallengeFragment
import com.example.canvart.ui.challenges.portraitChallenge.PortraitChallengeFragment
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
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun listeners(){
        binding.cntImageOverlay.setOnClickListener {
            goToImageChallenge()
        }
        binding.cntPortraitOverlay.setOnClickListener {
            if(binding.level!! >= 7){
                goToPortraitChallenge()
            }

        }
        binding.cntDescriptionOverlay.setOnClickListener {
            if(binding.level!! >= 14){
                goToDescriptionChallenge()
            }
        }
    }

    private fun goToImageChallenge(){
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        0,
                        R.anim.slide_out
                )
                replace(R.id.fcDetail, ImageChallengeFragment.newInstance())
                addToBackStack("")
            }
    }

    private fun goToPortraitChallenge(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, PortraitChallengeFragment.newInstance())
            addToBackStack("")
        }
    }

    private fun goToDescriptionChallenge(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, DescriptionChallengeFragment.newInstance())
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