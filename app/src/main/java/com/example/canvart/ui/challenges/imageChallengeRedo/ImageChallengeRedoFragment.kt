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
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.enums.Difficulty
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
            AppDatabase.getInstance(requireContext()).challengeDrawingDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE),
            requireArguments().getLong(ID_CHALLENGE, 0),
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.condition = viewModel
        viewModel.initTimerObject.start()

        setupToolbar()
        setupViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(!viewModel.countdownStartChecker.value!!){
            viewModel.stopTimer()
        }
        viewModel.initTimerObject.cancel()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        listeners()
        observers()
    }

    private fun setupToolbar(){
        binding.toolbar.run {
            title = getString(R.string.image_challenge_titlebox)
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
        viewModel.difficultyLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            if(result == Difficulty.TUTORIAL){
                requireActivity().supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            0,
                            R.anim.slide_out
                    )
                    replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(7, requireArguments().getLong(ID_CHALLENGE, 0)))
                    addToBackStack("")
                }
            }
            else{
                requireActivity().supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            0,
                            R.anim.slide_out
                    )
                    replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(4, requireArguments().getLong(ID_CHALLENGE, 0)))
                    addToBackStack("")
                }
            }
        })
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

        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 90f
        circularProgressDrawable.start()

        viewModel.timerMillis.observe(viewLifecycleOwner, Observer {
                result ->
            binding.lblTimer.text = viewModel.parseMillis(result)
            if(result in 1..1999){
                goToFinished()
            }
        })
        viewModel.url.observe(viewLifecycleOwner, Observer {
                result ->
            if(result.matches("-?\\d+(\\.\\d+)?".toRegex())){
                Glide.with(requireContext())
                    .load(result.toInt())
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(binding.imgUserChallenge)
            }
            else{
                Glide.with(requireContext())
                    .load(result)
                    .centerCrop()
                    .placeholder(circularProgressDrawable)
                    .into(binding.imgUserChallenge)
            }
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
            viewModel.countdownStartChecker.observe(viewLifecycleOwner, Observer {
                    checker ->
                if(!checker){
                    viewModel.startTimer(result)
                    binding.lblTimer.text = "∞"
                }
            })
        })
        viewModel.onInitTimer.observe(viewLifecycleOwner, Observer {
                result ->
            binding.lblCountdown.text = viewModel.parseMillisSeconds(result-1000)
            if(result in 1000..2000){
                viewModel.hideStartCoundown()
            }
        })
    }

    companion object{

        fun newInstance(id : Long) : ImageChallengeRedoFragment =
            ImageChallengeRedoFragment().apply {
                arguments = bundleOf(ID_CHALLENGE to id)
            }

    }
}