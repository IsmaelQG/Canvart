package com.example.canvart.ui.challenges.descriptionChallenge

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.databinding.FragmentDescriptionChallengeBinding
import com.example.canvart.databinding.FragmentPortraitChallengeBinding
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DescriptionChallengeFragment : Fragment(R.layout.fragment_description_challenge) {

    private val binding by viewBinding { FragmentDescriptionChallengeBinding.bind(it) }

    private val viewModel : DescriptionChallengeViewModel by viewModels{
        DescriptionChallengeViewModelFactory(
                AppDatabase.getInstance(requireContext()).componentCharacterDao,
                requireActivity().getPreferences(Context.MODE_PRIVATE),
                this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupViews()
        setupToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stopTimer()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        listeners()
        observers()
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

    private fun goBack(): Boolean {
        requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        return true
    }

    private fun goToFinished(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(3, viewModel.getListId()))
            addToBackStack("")
        }
    }

    private fun listeners(){
        binding.btnLeave.setOnClickListener {
            goBack()
        }
        binding.btnSubmit.setOnClickListener {
            goToFinished()
        }
    }

    private fun observers(){

        viewModel.timerMillis.observe(viewLifecycleOwner, Observer {
            result ->
            binding.lblTimer.text = viewModel.parseMillis(result)
            if(result in 1..1999){
                goToFinished()
            }
        })
        viewModel.component0.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component1.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component2.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component3.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component4.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component5.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component6.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component7.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component8.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component9.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component10.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.component11.observe(viewLifecycleOwner, Observer {
            viewModel.sum()
        })
        viewModel.difficultyLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            binding.lblDifficulty.text = viewModel.getDifficulty(result)
            binding.lblDifficulty.setBackgroundResource(viewModel.getResidBackground(result))
        })
        viewModel.materialLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            binding.lblMaterial.text = viewModel.getMaterial(result)
        })
        viewModel.timerLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            viewModel.startTimer(result)
            binding.lblTimer.text = "∞"
        })
        viewModel.condAllFound.observe(viewLifecycleOwner, Observer {
            result ->
            println(result)
            if(result == 12){
                binding.textDescriptionUserChallenge.text = viewModel.concatenate()
            }
        })
    }

    companion object {

        fun newInstance() : DescriptionChallengeFragment =
                DescriptionChallengeFragment()
    }
}