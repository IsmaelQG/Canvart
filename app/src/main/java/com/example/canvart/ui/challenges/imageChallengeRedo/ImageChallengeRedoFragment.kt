package com.example.canvart.ui.challenges.imageChallengeRedo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.databinding.FragmentImageChallengeRedoBinding
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ID_CHALLENGE = "ID_CHALLENGE"

class ImageChallengeRedoFragment : Fragment(R.layout.fragment_image_challenge_redo) {
    private val binding by viewBinding { FragmentImageChallengeRedoBinding.bind(it)}

    private val viewModel : ImageChallengeRedoViewModel by viewModels{
        ImageChallengeRedoViewModelFactory(
            AppDatabase.getInstance(requireContext()).imageURLDao,
            AppDatabase.getInstance(requireContext()).challengeDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE),
            requireArguments().getLong(ID_CHALLENGE, 0),
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar()
        setupViews()
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
            replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(4, requireArguments().getLong(ID_CHALLENGE, 0)))
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
        viewModel.url.observe(viewLifecycleOwner, Observer {
                result ->
            Glide.with(requireContext())
                .load(result)
                .into(binding.imgUserChallenge)
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
            binding.lblTimer.setText(R.string.infMinutes)
        })
    }

    companion object{

        fun newInstance(id : Long) : ImageChallengeRedoFragment =
            ImageChallengeRedoFragment().apply {
                arguments = bundleOf(ID_CHALLENGE to id)
            }

    }
}