package com.example.canvart.ui.challenges.portraitChallenge

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.databinding.FragmentPortraitChallengeBinding
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.example.canvart.ui.challenges.imageChallenge.ImageChallengeViewModel
import com.example.canvart.ui.challenges.imageChallenge.ImageChallengeViewModelFactory
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class PortraitChallengeFragment : Fragment(R.layout.fragment_portrait_challenge) {

    private val binding by viewBinding { FragmentPortraitChallengeBinding.bind(it) }

    private val viewModel : PortraitChallengeViewModel by viewModels{
        PortraitChallengeViewModelFactory(
                AppDatabase.getInstance(requireContext()).componentHeadDao,
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
            replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(2, viewModel.getListId()))
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
        })
        viewModel.condAllFound.observe(viewLifecycleOwner, Observer {
            result ->
            println(result)
            if(result == 7){
                binding.textPortraitUserChallenge.text = viewModel.concatenate()
            }
        })
    }

    companion object {

        fun newInstance() : PortraitChallengeFragment =
                PortraitChallengeFragment()
    }
}