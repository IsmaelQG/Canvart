package com.example.canvart.ui.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentTutorialBinding
import com.example.canvart.ui.adventure.AdventureAdapter
import com.example.canvart.ui.challenges.challengeShowDescription.ChallengeShowDescriptionFragment
import com.example.canvart.ui.challenges.challengeShowImage.ChallengeShowFragment
import com.example.canvart.ui.challenges.challengeShowPortrait.ChallengeShowPortraitFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

class TutorialFragment : Fragment(R.layout.fragment_tutorial) {

    private val binding by viewBinding { FragmentTutorialBinding.bind(it) }

    private val viewModel : TutorialViewModel by viewModels {
        TutorialViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDrawingDao
        )
    }

    private val listAdapter: AdventureAdapter by lazy {
        AdventureAdapter(
                AppDatabase.getInstance(requireContext()).challengeDrawingDao
        ).apply {
            setOnItemClickListener{
                println("hello")
                askType(currentList[it])
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupRecyclerView()
        setupViews()
    }

    private fun setupViews(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.toolbar.run {
            title = getString(R.string.tutorial_title)
            setNavigationIcon(R.drawable.ic_arrow_back_dark)
            setNavigationOnClickListener {
                goBack()
            }
        }
    }

    private fun setupRecyclerView(){
        binding.lstTutorials.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = ScaleInAnimationAdapter(AlphaInAnimationAdapter(listAdapter).apply {
                // Change the durations.
                setDuration(1000)
                // Change the interpolator.
                setInterpolator(OvershootInterpolator())
                // Disable the first scroll mode.
                setFirstOnly(false)
            })
        }
    }

    private fun observeViewModel(){
        viewModel.challenges.observe(viewLifecycleOwner, Observer {
            result ->
            viewModel.challengesWithDrawing.observe(viewLifecycleOwner, Observer {
                    resultDrawings ->
                if(resultDrawings.size < 5){
                    showChallenges(result.subList(0, resultDrawings.size+1))
                }
                else{
                    showChallenges(result)
                }
            })
        })
    }

    private fun showChallenges(challenges : List<Challenge>){
        listAdapter.submitList(challenges)
    }

    private fun askType(challenge: Challenge){
        viewModel.listIdChallengesImage.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsImage(challenge)
            }
        })
        viewModel.listIdChallengesPortrait.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsPortrait(challenge)
            }
        })
        viewModel.listIdChallengesDescription.observe(viewLifecycleOwner, Observer {
                result ->
            if(challenge.id in result){
                goToDrawingsDescription(challenge)
            }
        })
    }

    private fun goToDrawingsImage(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                0,
                R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowFragment.newInstance(challenge.id))
            addToBackStack("")
        }
    }

    private fun goToDrawingsPortrait(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                0,
                R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowPortraitFragment.newInstance(challenge.id))
            addToBackStack("")
        }
    }

    private fun goToDrawingsDescription(challenge : Challenge){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                0,
                R.anim.slide_out
            )
            replace(R.id.fcDetail, ChallengeShowDescriptionFragment.newInstance(challenge.id))
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

        fun newInstance() : TutorialFragment =
            TutorialFragment()

    }

}