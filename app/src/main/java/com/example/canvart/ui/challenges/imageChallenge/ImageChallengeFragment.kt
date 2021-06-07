package com.example.canvart.ui.challenges.imageChallenge

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.example.canvart.ui.challenges.challengeDone.ChallengeDoneFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.canvart.utils.viewBinding

class ImageChallengeFragment : Fragment(R.layout.fragment_image_challenge) {

    private val binding by viewBinding { FragmentImageChallengeBinding.bind(it)}

    private val viewModel : ImageChallengeViewModel by viewModels{
        ImageChallengeViewModelFactory(
            AppDatabase.getInstance(requireContext()).imageURLDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE),
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
            setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    0,
                    R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeDoneFragment.newInstance(1, viewModel.url))
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
        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        viewModel.timerMillis.observe(viewLifecycleOwner, Observer {
            result ->
            binding.lblTimer.text = viewModel.parseMillis(result)
            if(result in 1..1999){
                goToFinished()
            }
        })
        viewModel.urlList.observe(viewLifecycleOwner, Observer {
            result ->
            viewModel.url = result.random()
            Glide.with(requireContext())
                .load(viewModel.url)
                .placeholder(circularProgressDrawable)
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
            binding.lblTimer.text = "∞"
        })
    }

    companion object{

        fun newInstance() : ImageChallengeFragment =
            ImageChallengeFragment()

    }

}