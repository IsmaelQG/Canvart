package com.example.canvart.ui.adventure

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.databinding.FragmentAdventureBinding
import com.example.canvart.ui.challenges.challengeShowDescription.ChallengeShowDescriptionFragment
import com.example.canvart.ui.challenges.challengeShowImage.ChallengeShowFragment
import com.example.canvart.ui.challenges.challengeShowPortrait.ChallengeShowPortraitFragment
import com.example.canvart.ui.tutorial.TutorialFragment
import com.example.canvart.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

private const val LEVEL_UP_DIALOG_TAG = "LEVEL_UP_DIALOG_TAG"

class AdventureFragment : Fragment(R.layout.fragment_adventure) {

    private val binding by viewBinding { FragmentAdventureBinding.bind(it) }

    private val viewModel : AdventureViewModel by viewModels {
        AdventureViewModelFactory(
            AppDatabase.getInstance(requireContext()).challengeDrawingDao,
            requireActivity().getPreferences(Context.MODE_PRIVATE)
            )
    }

    private val listAdapter: AdventureAdapter by lazy {
        AdventureAdapter(
            AppDatabase.getInstance(requireContext()).challengeDrawingDao
        ).apply {
            setOnItemClickListener{
                askType(currentList[it])
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupToolbar()
        setupViews()
        setupRecyclerView()
    }

    private fun setupToolbar(){
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.VISIBLE
        binding.toolbar.run {
            title = getString(R.string.adventure_text)
            inflateMenu(R.menu.menu_list_adventure)
            setOnMenuItemClickListener { onMenuItemClick(it) }
        }
    }

    private fun setupViews(){
        observeViewModel()
    }

    private fun setupRecyclerView(){
        binding.lstAdventure.run {
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

    private fun onMenuItemClick(item : MenuItem) : Boolean{
        when(item.itemId){
            R.id.mnuTutorial -> navigateToTutorial()
            else -> return false
        }
        return true
    }
    private fun navigateToTutorial(){
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fcDetail, TutorialFragment.newInstance())
            addToBackStack("")
        }
    }

    private fun observeViewModel(){
        viewModel.challenges.observe(viewLifecycleOwner, Observer {
            result ->
            viewModel.challengesWithDrawing.observe(viewLifecycleOwner, Observer {
                resultDrawings ->
                if(resultDrawings.size < 15){
                    showChallenges(result.subList(0, resultDrawings.size+1))
                }
                else{
                    showChallenges(result)
                }
                viewModel.level.observe(viewLifecycleOwner, Observer {
                        level ->
                    if(resultDrawings.size > level){
                        viewModel.levelUp(level)
                        showLevelUpDialog(level+1)
                    }
                })
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

    private fun showLevelUpDialog(level : Int) {
        AdventureLevelUpDialogFragment(level)
            .show(parentFragmentManager, LEVEL_UP_DIALOG_TAG)
    }

    companion object{

        fun newInstance() : AdventureFragment =
                AdventureFragment()

    }

}