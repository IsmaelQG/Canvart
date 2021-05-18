package com.example.canvart.ui.challenges

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.databinding.FragmentImageChallengeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.example.canvart.utils.viewBinding

class ImageChallengeFragment : Fragment(R.layout.fragment_image_challenge) {

    private val binding by viewBinding { FragmentImageChallengeBinding.bind(it)}

    private val viewModel : ImageChallengeViewModel by activityViewModels(){
        ImageChallengeViewModelFactory(
            AppDatabase.getInstance(requireContext()).imageURLDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE)
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
            replace(R.id.fcDetail, ChallengeDoneFragment.newInstance())
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
            result -> binding.lblTimer.text = viewModel.parseMillis(result)
        })
        viewModel.urlList.observe(viewLifecycleOwner, Observer {
            result ->
            viewModel.url = result.random()
            Picasso.get().load(viewModel.url).into(binding.imgUserChallenge)
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
    }

    companion object{

        fun newInstance() : ImageChallengeFragment =
            ImageChallengeFragment()

    }

}